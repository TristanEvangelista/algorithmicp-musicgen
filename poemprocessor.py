"""
Module for processing poem text
Performs overall analysis and section by section analysis
"""
import os
import collections
import inflect
import nltk
import json
import re
from itertools import groupby
from nltk.tokenize import word_tokenize, RegexpTokenizer
from nltk.text import Text
from nltk.corpus import stopwords, cmudict

tokenizer = RegexpTokenizer(r'\w+')
stop_words = set(stopwords.words('english'))
p = inflect.engine()
dictionary = cmudict.dict()

def nsyl(word):
    """
    Processes word
    Returns amount of syllables using CMU speech dictionary
    """


    try:
        answerlist = [len(list(y for y in x if y[-1].isdigit())) for x in dictionary[word]]
        return answerlist[0]
    except KeyError:

        return syllables(word)

def syllables(word):
#referred from stackoverflow.com/questions/14541303/count-the-number-of-syllables-in-a-word
    """
    Processes word if not found in CMU speech dictionary
    Returns estimate of number of syllables
    """
    count = 0
    vowels = 'aeiouy'
    word = word.lower()
    if word[0] in vowels:
        count +=1
    for index in range(1,len(word)):
        if word[index] in vowels and word[index-1] not in vowels:
            count +=1
    if word.endswith('e'):
        count -= 1
    if word.endswith('le'):
        count+=1
    if count == 0:
        count +=1
    return count

def getSyllables(text):
    """
    Processes whole text
    Returns amount of syllables and rests
    """
    totalLines = len(text);
    syllablemap = [None]*totalLines
    for i in range(totalLines):
        text[i].lower()
        wordline = text[i]
        print(wordline)
        wordslistinline = re.split(';|,|\.|\?|\!|\s' ,wordline)
        while("" in wordslistinline) : 
            wordslistinline.remove("") 
        print(wordslistinline)
        lengthofline = len(wordslistinline)
        currentSyllables = [None]*lengthofline
        for j in range(lengthofline):
            syllablesinword = nsyl(wordslistinline[j])
            print(syllablesinword)
            currentSyllables[j] = syllablesinword
        syllablemap[i] = currentSyllables

    return syllablemap




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
    found_words = []
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
                if word in filtered_words:
                    continue

                if word in stop_words:
                    filtered_stopwords.append(word)
                    continue
                             
                else:
                    word_count += 1

                if word not in lexicon:
                    ow = word
                    if p.singular_noun(ow) in lexicon:
                        word = p.singular_noun(ow)

                    if p.plural(word) in lexicon:
                        word = p.plural(ow)
                
                if word in lexicon:
                    word_used += 1
                    found_words.append(word)
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
    print("Found words")
    print(found_words)
    print("Words not in lexicon")
    print(filtered_words)
    print(word_used, " out of ", word_count, "words.")
    return (overall, profile)

lexicon = generate_lexicon("lexicon.txt")
text = get_poem("sample1-Tyger.txt")

#syllableList = getSyllables(text)

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
    top_emotion = list(sorted_dict)[0]
    emotions.append((top_emotion, sorted_dict.get(top_emotion)))

print(sorted_profile)
overall_emo = list(sorted_dict)[0]
print("The overall emotion is: ", overall_emo)
print(text)

emotion_profiles = {}
emotion_profiles["overall"] = overall

with open('parameters.txt', 'w') as outfile:
    outfile.write(str(len(profile)))
    outfile.write("\n")
    outfile.write("\n")
    for e in overall:
        outfile.write(e + " " + str(overall.get(e)))
        outfile.write("\n")

    outfile.write("\n")
    for emotion in emotions:
        emo, value = emotion
        outfile.write(emo + " " + str(value))
        outfile.write("\n")


with open('data.json', 'w', encoding='utf-8') as f:
    data = {}
    data["numSections"] = len(profile)
    emotionlist = []
    for e in overall:
        emotionlist.append(overall.get(e))
    print(overall)
    data["overall"] = emotionlist

    sectionemotions = []
    sectionintensity = []

    for emotion in emotions:
        emo, value = emotion
        sectionemotions.append(emo)
        sectionintensity.append(value)
    data["emotions"] = sectionemotions
    data["intensity"] = sectionintensity
    #data["syllables"] = syllableList
    #data["rests"] = 0
    #data["locationsofRest"] = 0
    print(data)
    for idx, s in enumerate(profile):
        emotion_profiles["section#" + str(idx+1)] = s
    #print(emotion_profiles)

    json.dump(data, f, ensure_ascii=False, indent=4)



        