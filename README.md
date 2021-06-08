# SO2-exam-simulator
App giocattolo per l'esame "quiz-patente" di Sistemi Operativi 2 - Sapienza, Informatica.

Semplice app **da terminale** che si occuperà di addestrare il cadetto all'esame di SO2.

## Disclaimer
---
Ci solleviamo da ogni responsabilità di utilizzo improprio di questo strumento.
Utilizzalo a tuo rischio e pericolo, ma soprattutto non venirci a dire "eh ma questa domanda non c'era" a compito di esame finito (magari puoi aggiungerla tu e aiutare i tuoi colleghi).

---

## Istruzioni
Clonare questo repo usando `git`, **oppure** scaricare e scompattare lo zip di questa repo.

Per linux/mac:
```sh
git clone https://github.com/andrea-gasparini/SO2-exam-simulator.git
cd SO2-exam-simulator
java -jar SO2Quiz.jar
```

Per Windows il procedimento é il medesimo da prompt dei comandi, recarsi nella cartella appena clonata / scaricata e digitare `java -jar SO2Quiz.jar`

Fare in ogni caso riferimento a _WINDOWS__README.TXT_.


## Requisiti
L'app é stata sviluppata e testata con Java 10, pertanto è fortemente consigliato scaricare Java 10 dal sito della Oracle.

## Come contribuire
Per **aggiungere** o **modificare** le domande: `dependencies/questions.txt`

Oppure dai un'occhiata alla [TODO List dei desideri](#todo-list-dei-desideri).

## TODO List dei desideri
- [ ] **GUI cross-platform** (con JavaFX o con altro, ogni proposta è ben accetta, soprattutto JS, purché giri su Github Sites e funzioni).
- [ ] Stessa app che **include i quesiti di SO1** (ovviamente senza stravolgere il codice attuale: la versione so1 è selezionabile all'inizio oppure tramite parametri da linea di comando).

Per qualsiasi cosa, **apri una PR** o una **issue**.
