#This code will focus on analyzing text and then trying to create output music

#MIDIUtil is used to create MIDI files
#pip install MIDIUtil
from midiutil import MIDIFile

#Reads the textfile and produces string of poem
poemFile = open("poem.txt","r")
if poemFile.mode == 'r':
    poem = poemFile.read()

#Creates list from string of poem
poemLines = poem.splitlines()

#Instantiates number of empty list arrays indicating line # in poem
poemWords = []*len(poemLines)

#instantiates test note list
poemTestDegrees = []

for poemLineSpecific in poemLines:
    poemWords.append(poemLineSpecific.split())
    for poemWordSpecific in poemWords:
        poemTestDegrees.append(57 + (len(poemWordSpecific)))
#print(poemTestDegrees)

#Analyze Poem as awhole
#Analyze Poem line by line
#Seperate analysis of periods, commas, hyphens, etc.
# in words analze placement of apostrophes
#print(poem)

#Defining variables in music
degrees  = poemTestDegrees  # MIDI note number
track    = 0
channel  = 0
time     = 0    # In beats
duration = 1    # In beats
tempo    = 60   # In BPM
volume   = 100  # 0-127, as per the MIDI standard

#print(degrees)
# One track, defaults to format 1 (tempo track is created
# automatically)
MyMIDI = MIDIFile(1)  
MyMIDI.addTempo(track, time, tempo)

for i, pitch in enumerate(degrees):
    MyMIDI.addNote(track, channel, pitch, time + i, duration, volume)

with open("outputmusic.midi", "wb") as output_file:
    MyMIDI.writeFile(output_file)

