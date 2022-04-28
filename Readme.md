# Examen pràctic DSI. Solucionat!
## ATENCIÓ: la part de missatgeria està feta amb la versió antiga


L'examen parteix del codi de la pràctica 4 resolt, on hi ha el següent:
* **notes**: el microservei que gestiona les notes. Quan se li envia una nota nova d'un client fa una crida síncrona al microservei
  de **users** per comprovar si existeix. Aquesta crida està protegida amb Hystrix de manera que si l'**user** no respon es guarda la nota a la BBDD
  amb la marca de *checked=false*, tot esperant de poder fer la comprovació més endavant. Aquesta darrera comprovació no està
  codificada (adivina què hauràs de fer :).
* **users**: el mocroservei que gestiona usuaris. Quan se li demana que esborri un usuari ho fa de la seva BBDD i envia
  un missatge de forma assíncrona a **notes** per tal que les notes de l'usuari també s'esborrin. Això també està codificat.
* **reverseproxy**: és un microservei Zuul que fa de punt d'entrada al sistema. Les crides que comencen per *notes* les
  redirigeix al microservei de **notes** i les que comencen per *users* les envia al de **users**
* **discovery**: és un microservei Eureka que fa de descobriment dels microserveis.

## Què haig de fer?
Com ja he anunciat hauràs d'ampliar els microserveis de **notes** i **users** per tal que si falla la comunicació síncrona on
**notes** demana a **users** per l'existència d'un usuari, aquesta comprovació es faci de forma assíncrona de la següent manera:

Suposem que el microservei **users** està caigut:
* Al crear-se una nota nova no es pot fer la comprovació síncrona però de totes maneres **notes** guarda la nota marcada
  com a *checked=false* (això ja està programat i ho trobaràs a NotesRESTController)
* Al punt anterior hauràs de fer que **notes** enviï un missatge (a través de RabbitMQ) demanant la comprovació de l'usuari.
  Com el servei **users** està caigut aquest missatge quedarà emmagatzemat al servei RabbitMQ. Aquest missatge l'hauràs d'enviar
  per un canal diferent al dels missatges d'esborrar notes. Per exemple el pots anomenar *question*.
* Quan **users** s'aixequi haurà de rebre el(s) missatge(s). Farà la comprovació i enviarà un missatge per un altre canal amb el resultat
  de la comprovació. El canal es pot anomenar *answer* per exemple.
* Quan **notes** reb el missatge amb el resultat de la comprovació, esborrarà els missatges si l'usuari no existeix o marcarà
  la nota com a checked si l'usuari existeix. A NoteUseCases trobareu el mètode *updateNoteExists(String userName)* que ho fa.

## Crides als microserveis

### Crear nota
* http://localhost:8080/notes/api/notes
* **Action:** POST
* **Headers:** Content-Type=application/json
* **body:**
```json
{
"title": "Examen DSI",
"content": "Aquest examen està xupat",
"dateCreation": "2018-02-20 15:19:50",
"dateEdit": "2018-02-20 15:19:50",
"userName": "castells"
}
```

### Esborrar Usuari
* http://localhost:8080/users/api/users/castells
* **Action:** DELETE

### Llistar tots els usuaris
* http://localhost:8080/users/api/users/
* **Action:** GET

### Llistar totes les notes
* http://localhost:8080/notes/api/notes/
* **Action:** GET

## Referències per enviar i rebre missatges per més d'un canal
* Tutorial: http://www.baeldung.com/spring-cloud-stream especialment l'apartat 4
* Codi exemple: https://github.com/spring-cloud/spring-cloud-stream-samples/tree/master/multi-io-samples/multi-io
