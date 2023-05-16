# How to run:
- java -jar alg_aho_corasick.jar [option]
## options:
- counter: counts occurrences of words in text
- signal: prints positions of words in text
- replacer: replaces words in text
- censor: removes/censors words in text
  - if you want to remove occurrences of words, you leave the other argument empty (e.g. java -jar alg_aho_corasick.jar censor)
  - if you want to censor words with some character, you have to enter it as argument (e.g. java -jar alg_aho_corasick.jar censor [character])

<p>Then you have to enter words to search for (one per line) and end input with "END"</p>
<p>if you want to use replacer option, you have to enter [word]:[replacement] per line and end input with "END"</p>
<p>Then you have to enter text to search in and end input with end of input shortcut (e.g. ctrl + z)</p>