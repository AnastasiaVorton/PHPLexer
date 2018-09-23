#PHP lexical analyser

(done by Bulat Khabirov and Anastasiia Repryntseva)

#### About the project
Lexical analyser for PHP programming language (version > 7.0). Done using Java 8.
In our architecture we decided to use regular expressions to match lexemes to tokens. 

#### About tokens
All the tokens can be found in [PHPToken.java](./src/main/java/phplexer/PHPToken.java) file. 
Most of the tokens were taken from the official [PHP documentation](http://php.net/manual/en/tokens.php)
but were customized according to out vision of the architecture of this project.
All the keywords, operators etc were gathered into groups. 

#### The algorithm 
- Step 1 :
if buffer is not empty go to *step 3*;
- Step 2: read into the buffer until the end of the token (whitespace/line break);
- Step 3: find longest regexp match;
- Step 4: if found, return and delete the lexeme from the buffer;

P.S. Comments, heredocs and string literals are beyond the scope of the algorithm described above and are handled manually.

#### Input/Output
Source code for the processing is taken from the file [in.txt](in.txt) and the list of tokens is placed into
[out.txt](out.txt) file. The format of the output is *"lexeme lexeme: token_name"*. The meaning of each token again can
 be seen in [PHPToken.java](./src/main/java/phplexer/PHPToken.java) file. 
 
 #### How to run and test 
*If you use UNIX system substitute **gradle** for ./gradlew
and if you use windows - substitute for gradlew.bat:*
- Step 1: cd *project folder*
- Step 2: gradle build
- Step 3: gradle run
- Step 4: (to run tests:) gradle test 

#### Last words
We desided on Oracle's Java code conventions.
Intended line length - 120 symbols.
Project also includes javadoc-compatible documentation.
 

