=== RISOLVERE IL PROBLEMA DEGLI ACCENTI SUL TERMINALE DI WINDOWS ===

Non è un problema di Java, ma di Windows che non ha impostato automaticamente la "code page" di utf-8.

Ci sono due modi per risolvere la cosa:
	
	1.	Temporaneo
			Prima di lanciare l'applet si dà il comando chcp 65001.
			Se ancora non funzionasse, si lancia l'applet con l'opzione aggiuntiva "-Dfile.encoding=UTF-8".
			Dovrete ripeterlo ogni volta.

	2.	Permanente
			Prevede una piccola modifica al registro di sistema, ovviamente non ci assumiamo la responsabilità delle vostre azioni,
			si raccomanda di eseguire una copia di backup del registro prima della modifica e tutte queste belle cose.

			WIN+X -> esegui -> regedit -> ora siete nell'editor del registro di sistema, navigate fino a
			"HKEY_LOCAL_MACHINE\Software\Microsoft\Command Processor".
			Controllate se c'è una voce di tipo stringa (hanno l'icona 'ab' rossa) nominata "Autorun":
			se c'è
				doppio clic -> cambiate il campo "Dati valore" in "@chcp 65001>nul"
			altrimenti 
				tasto destro -> nuovo -> valore stringa -> nominatelo "Autorun" -> doppio clic -> inserite "@chcp 65001>nul" nel campo "Dati valore"

			Chiudete l'editor, non c'è bisogno di salvare o riavviare il PC.


In entrambi i casi, dando il comando "chcp" senza argomenti dovrebbe dirvi "Tabella codici attiva: 65001".