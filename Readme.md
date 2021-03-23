# Examen DSI 

### TODOS
* Posar el restTemplate a un port quan es crea una nota nova d'un usuari
* Protegir la crida anterior i fer que es guardi amb checked = false
* Quan s'esborra un usuari s'envia un missatge i s'esborren totes les seves notes. Fer que si hi ha més d'un servei de notes 
  només ho rebi un de sol.
* Discovery service (compte amb adreça del restTemplate)
* Gateway de manera que si comença en /users vagi a usuari i si comença en /notes vagi a notes
