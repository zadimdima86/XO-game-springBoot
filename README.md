"# XO-game-springBoot" 
Voici les prérequis nécessaires du projet :

	Java 8 (JK1.8)

	Apache Maven 3.5.4

	Intellij-IDEA 2019.2.1 ou autre IDE

	Git 2.18.0

	Postgres 10

Dependences utilisées:

lombok

Etapes

Téléchargez et décompressez le code source, ou clonez-le à l'aide de la commande Git suivante: « git clone https://github.com/zadimdima86/XO-game-springBoot.git »

Pour assurer le bon fonctionnement de l ’application : 

	Compiler le projet avec la commande : mvn clean install

	Créer la base de données : tictactoe (postgres/root)

   Lancez le projet avec la commande : mvn spring-boot:run

Comment Jouer?

Créer 2 players via l'API: http://localhost:8081/player/create

Créer le game avec un 1er joueur via l'API:  http://localhost:8081/game/create

Ajouter le 2eme joueur au game  via l'API: http://localhost:8081/game/join

Commencer à jouer en creant le 1er move via l'API: http://localhost:8081/move/create ,  ainsi de suite

