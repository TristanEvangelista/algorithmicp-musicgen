"""
Module for processing poem text
Performs overall analysis and section by section analysis
"""
import os
import collections
import nltk
import json
from itertools import groupby
from nltk.tokenize import word_tokenize, RegexpTokenizer
from nltk.text import Text
from nltk.corpus import stopwords

tokenizer = RegexpTokenizer(r'\w+')
stop_words = set(stopwords.words('english'))

def generate_lexicon(lex_filename):
    """
    Processes lexicon
    Returns lexicon dictionary
    """

    # Access lexicon file
    with open(lex_filename) as f:
        wordlist = list(f)

    lexicon = {}
    for line in wordlist:
        word = line.split()[0]
        emotion = line.split()[1]
        emo_score = line.split()[2]

        if word in lexicon:
            lexicon[word][emotion] = emo_score 
        else:
            lexicon[word] = {emotion: emo_score}
    
    return lexicon

def get_poem(filename):
    """
    Return list of lines in poem
    """
    text = open(filename).read().splitlines()
    return text

# Poem accessing

def generate_profile(text):
    """
    Text analysis on poem
    Returns overall and section by section profile
    """
    word_count = 0
    word_used = 0
    filtered_stopwords = []
    filtered_words = []
    overall = {}
    profile = []

    # split the sections, separated by a linebreak
    sections = [list(group) for k, group in groupby(text, lambda x: x == "") if not k]


    for x in range(0, len(sections)):
        section_profile = {}
        for y in range(0, len(sections[x])):
            tokens = tokenizer.tokenize(sections[x][y])
            tokens = [word.lower() for word in tokens]
            for word in tokens:
                if word in stop_words:
                    filtered_stopwords.append(word)
                    continue
                
                else:
                    word_count += 1
                
                if word in lexicon:
                    word_used += 1
                    for key in lexicon[word]:
                        if key in section_profile:
                            section_profile[key] += int(lexicon[word][key])
                        else:
                            section_profile[key] = int(lexicon[word][key])

                        if key in overall:
                            overall[key] += int(lexicon[word][key])
                        else:
                            overall[key] = int(lexicon[word][key])

                else:
                    filtered_words.append(word)

        profile.append(section_profile)

    print("Filtered stopwords")
    print(filtered_stopwords)
    print("Words not in lexicon")
    print(filtered_words)
    print(word_used, " out of ", word_count, "words.")
    return (overall, profile)

lexicon = generate_lexicon("lexicon.txt")
text = get_poem("poem-1.txt")

overall, profile = generate_profile(text)
overall.pop("positive")
overall.pop("negative")

emotions = []

sorted_profile = sorted(overall.items(), key=lambda kv: kv[1], reverse = True)
sorted_dict = collections.OrderedDict(sorted_profile)


for idx, p in enumerate(profile):
    p.pop("positive")
    p.pop("negative")
    sorted_profile = sorted(p.items(), key=lambda kv: kv[1], reverse = True)
    sorted_dict = collections.OrderedDict(sorted_profile)

    print("The top emotion for section # ", idx+1, " is: ", list(sorted_dict)[0])
    emotions.append(list(sorted_dict)[0])

print(sorted_profile)
overall_emo = list(sorted_dict)[0]
print("The overall emotion is: ", overall_emo)

emotion_profiles = {}
emotion_profiles["overall"] = overall
with open('data.txt', 'w') as outfile:
    for idx, s in enumerate(profile):
        emotion_profiles["section#" + str(idx+1)] = s

    print(emotion_profiles)
    json.dump(emotion_profiles, outfile)