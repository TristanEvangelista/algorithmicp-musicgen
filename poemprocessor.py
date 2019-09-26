"""
Module for processing poem text
"""
import os
import collections
import nltk
from nltk.tokenize import word_tokenize, RegexpTokenizer
from nltk.text import Text
from nltk.corpus import stopwords
# Lexicon accessing

with open("lexicon.txt") as f:
    my_list = list(f)


def generate_lexicon():
    lexicon = {}
    for line in my_list:
        word = line.split()[0]
        emotion = line.split()[1]
        emo_score = line.split()[2]

        if word in lexicon:
            lexicon[word][emotion] = emo_score 
        else:
            lexicon[word] = {emotion: emo_score}
    
    return lexicon

def process_emotion(text):
    tokenizer = RegexpTokenizer(r'\w+')
    tokens = tokenizer.tokenize(text)
    tokens = [word.lower() for word in tokens]


# Poem accessing

poem_name = "poem-1"

text = open("poem-1.txt").read().splitlines()
num_lines = sum(1 for line in text)

profile = {}
count = 0

section_lines = num_lines/3

lexicon = generate_lexicon()

# divide lines by 3

# each section has num_lines/3 lines
# each iteration has num_lines/3 
sections = []
tokenizer = RegexpTokenizer(r'\w+')
stop_words = set(stopwords.words('english'))
filtered_words = []
word_count = 0
word_used = 0

profile = []
overall = {}

    
for x in range(0, 3):
    section_profile = {}
    for y in range(0, int(section_lines) + 1):
        if count != num_lines:
            tokens = tokenizer.tokenize(text[count])
            tokens = [word.lower() for word in tokens]
            for word in tokens:
                if word in stop_words:
                    filtered_words.append(word)
        
                else:
                    word_count += 1   
            
                if word in lexicon and word not in stop_words:
                    word_used += 1
                    print("Found in lexicon: ", word)
                    for key in lexicon[word]:
                        if key in section_profile:
                            section_profile[key] += int(lexicon[word][key])
                        else:
                            section_profile[key] = int(lexicon[word][key])

                        if key in overall:
                            overall[key] += int(lexicon[word][key])
                        else:
                            overall[key] = int(lexicon[word][key])

            count += 1
    profile.append(section_profile)

emotions = []
for idx, p in enumerate(profile):
    p.pop("positive")
    p.pop("negative")
    sorted_profile = sorted(p.items(), key=lambda kv: kv[1], reverse = True)
    sorted_dict = collections.OrderedDict(sorted_profile)

    print(sorted_profile)
    print("The top emotion for this section is: ", list(sorted_dict)[0])
    emotions.append(list(sorted_dict)[0])

overall.pop("positive")
overall.pop("negative")

sorted_profile = sorted(overall.items(), key=lambda kv: kv[1], reverse = True)
sorted_dict = collections.OrderedDict(sorted_profile)

print(sorted_profile)
overall_emo = list(sorted_dict)[0]
print("The overall emotion is: ", overall_emo)

with open("output.txt", "w") as file:

    for emotion in emotions:
        file.write(emotion + "\n")
    file.write(overall_emo)