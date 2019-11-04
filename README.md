# Api Start Wars - Mongo

Api permite criar planeta desde que o mesmo esteja na api publica do start wars, lista, deletar, buscar por id,
buscar por nome e deletar.


A aplicação disponibiliza alguns endpoints 


### API

 - **Busca todo os planetas**

    - **[+GET+]** -  [http://localhost:8000/v1/planets](http://localhost:8000/v1/planets)
    
 - **Busca planeta por id**
 
     - **[+GET+]** -  [http://localhost:8000/v1/planets/id](http://localhost:8000/v1/planets/id)
     
 - **Busca planeta por nome**
 
     - **[+GET+]** -  [http://localhost:8000/v1/planets/?name=](http://localhost:8000/v1/planets/?name=)     
        
    
- **Delete planet por id**
    
    - **[+Delete+]** - [http://localhost:8000/v1/planets/id](http://localhost:8000/v1/planets/id])
    
- **criar  planeta**  
    
    - **[+POST+]** - [http://localhost:8000/v1/planets](http://localhost:8000/v1/planets/)
    
    

### Executar aplicação

mvn clean install
mvn spring-boot:run

### Payload de entrada.
metodo post; { "name": "Tatooine" }
Obs. resto da informações será pego da api start wars publica



Obs. Não é necessario para executar a aplicação, mas é recomendado instalar o puglin do lombok na idea.

## Tecnologias usadas.

- spring-boot
- spring data
- java 8
- lombok
- maven
- mongo


## Divida Tecnicas

Não foram feito od teste unitario e nem intregado, apaser da sua importancia.
Não foi feito processo paralelo para as varias chamadas a api publica.




