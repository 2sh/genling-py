#Creator Linguarum

This is a personal project of mine to develop an application with which
it is possible to create a spoken contructed language, a conlang.
I have made many attempts at this over the years, using different
programming languages and new programming methods I have learned.

In this attempt I'm using Java due to its processing speed and cross-platform
capabilities. The generator makes use of user input
regex for deciding whether a generated word stem is allowed or not
(e.g. combinations of phonemes that are not permitted) and in transliterating
a generated word/stem from a "raw" representation (e.g. "<ge><hYo><uN><e><nu>")
into a "final" representation (e.g. "gehyōn'enu", "げひょうんえぬ", ).
In the generating process user input weights and probabilities are used
which affect the usage of individual phonemes, usage of syllable segments,
the length of a stem and whether a stem caught by a filter is to be let
through or not.

So far this is only a word/stem generator. As such, I plan to add a
database for holding the configurations and dictionary, and to create a
control GUI. I would like to add more on generating a language structure
but with my current knowledge of spoken languages and their complexities,
I am not yet certain on how to proceed from here.
