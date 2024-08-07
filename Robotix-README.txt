Manuel d'utilisation README décrivant comment utiliser l'application
-------------------------------------------------------------------------------------------
>> TITRE DU PROJET
Robotix: Logiciel de gestion de robots


>> DESCRIPTION DU PROJET
Robotix est une application de gestion de robots qui permet aux utilisateurs de contrôler les 
mouvements de leurs robots, d'allouer des tâches spécifiques en fonction des capacités des robots 
et de visualiser les métriques de performance. L'application offre également des fonctionnalités de 
recherche et de contact avec des fournisseurs de composants pour simplifier le processus d'achat et de 
maintenance des robots. Les utilisateurs peuvent aussi créer et participer à des activités collaboratives. 
Pour les fournisseurs, Robotix permet l'enregistrement, la modification et la vente de composantes 
directement via le marché interne 'Robotix Marketplace'.


>> FONCTIONNALITÉS IMPLÉMENTÉES
a) Contrôle des robots :
Les utilisateurs peuvent contrôler leurs robots et les diriger en leur donnant des commandes 
de déplacement précises, y compris la vitesse et la direction.

b) Allocation de tâches :
Les utilisateurs peuvent attribuer des tâches spécifiques aux robots en fonction de leurs 
capacités et disponibilités.

c) Visualisation des métriques de performance :
L'application fournit aux utilisateurs des rapports sur l'efficacité énergétique, l'utilisation 
des ressources, la consommation de batterie, et d'autres indicateurs de performance pour chaque 
robot.

d) Participation aux activités :
Les utilisateurs peuvent créer et rejoindre différentes activités au cours desquelles ils 
pourront échanger avec d'autres utilisateurs et mettre leurs robots au travail.

e) Recherche de fournisseurs de composants :
Robotix aide à trouver et à contacter des fournisseurs de composants pour robots, simplifiant 
ainsi le processus d'achat et de maintenance des robots.

f) Signalement des problèmes :
Les utilisateurs peuvent signaler des problèmes ou des anomalies détectées dans le fonctionnement 
des robots.

g) Inscription et connexion des utilisateurs :
Les utilisateurs peuvent créer un compte et se connecter à leur compte.

h) Gestion des robots :
Enregistrement de nouveaux robots, suppression des robots existants et consultation des vues 
générale et complète des robots.

i) Gestion du profil utilisateur :
Modification du nom d'utilisateur et du mot de passe.

j) Marketplace :
Recherche de composantes par nom, type ou fournisseur, consultation des détails et achat de 
composantes disponibles.

k) Gestion des activités :
Recherche d'activités par nom, date ou popularité, inscription et désinscription aux activités.

l) Notifications :
Consultation des notifications relatives aux activités et achats.

m) Gestion des composantes pour les fournisseurs :
Enregistrement de nouvelles composantes, modification des informations des composantes existantes 
et suppression des composantes.


>> ORGANISATION DES FICHIERS
application/src/main/java : 
Contient le code source de l'application ainsi que le fichier exécutable .jar.

application/src/test/java : 
Contient les tests JUnit.

application/target/classes : 
Répertoire de sortie pour les fichiers compilés .class.

application/pom.xml : 
Fichier de configuration utilisé par Maven.


>> DESCRIPTION DES DONNÉES DE DÉPART :
CLIENTS ET ROBOTS : Les données de départ incluent 10 clients, chacun possédant une flotte de 
2 robots avec des configurations et des composants spécifiques.
-Client 1 : Nom d'utilisateur: toto, Mot de passe: bobo
Robot 1 : Bender (KD-09290) avec CPU, Bras, Caméra, Haut-Parleur
Robot 2 : AlphaTron (AT-00123) avec CPU, Bras, Caméra, Roue
-Client 2 : Nom d'utilisateur: coco, Mot de passe: lolo
Robot 1 : BetaGuard (BG-00456) avec CPU, Bras, Caméra, Micro, Roue
Robot 2 : GammaCook (GC-00789) avec CPU, Bras, Haut-Parleur, Hélice
-Client 3 : Nom d'utilisateur: popo, Mot de passe: momo
Robot 1 : DeltaMed (DM-01012) avec CPU, Bras, Caméra, Micro, Écran
Robot 2 : EpsilonClean (EC-01345) avec CPU, Bras, Caméra, Roue
-Client 4 : Nom d'utilisateur: Guillaume, Mot de passe: Frisbee
Robot 1 : ZetaBot (ZB-01678) avec CPU, Bras, Micro, Écran
Robot 2 : EtaGard (EG-01901) avec CPU, Bras, Hélice, Écran
-Client 5 : Nom d'utilisateur: Nikolas, Mot de passe: Crypto
Robot 1 : ThetaLab (TL-02234) avec CPU, Bras, Micro, Haut-Parleur
Robot 2 : IotaBuild (IB-02567) avec CPU, Bras, Micro, Roue
-Client 6 : Nom d'utilisateur: Jordan, Mot de passe: VFX
Robot 1 : KappaDrone (KD-02890) avec CPU, Hélice, Micro, Caméra, Écran
Robot 2 : LambdaLift (LL-03123) avec CPU, Bras, Roue, Caméra
-Client 7 : Nom d'utilisateur: Medhi, Mot de passe: Couscous
Robot 1 : MuPet (MP-03456) avec CPU, Bras, Roue, Caméra, Micro
Robot 2 : NuComm (NC-03789) avec CPU, Roue, Caméra, Micro, Écran
-Client 8 : Nom d'utilisateur: Amal, Mot de passe: DaQueen
Robot 1 : XiCare (XC-04112) avec CPU, Bras, Roue, Caméra, Micro
Robot 2 : OmicronClean (OC-04445) avec CPU, Bras, Roue, Caméra, Micro, Écran
-Client 9 : Nom d'utilisateur: Johnny, Mot de passe: test
Robot 1 : PiFix (PF-04778) avec CPU, Roue, Caméra, Micro, Hélice
Robot 2 : RhoGuard (RG-05101) avec CPU, Roue, Caméra, Micro, Hélice, Écran
-Client 10 : Nom d'utilisateur: Gateau, Mot de passe: vanille
Robot 1 : SigmaChef (SC-05434) avec CPU, Roue, Bras, Micro, Hélice
Robot 2 : TauTutor (TT-05767) avec CPU, Roue, Bras, Micro, Hélice, Écran

FOURNISSEURS ET COMPOSANTES : Les données de départ incluent également 5 fournisseurs, 
chacun fournissant une variété de composants pour les robots :
-Fournisseur 1 : SKF Group
Composants : CPU, Roue, Bras, Haut-Parleur, Écran
-Fournisseur 2 : Bosch Rexroth
Composants : CPU, Hélice, Bras, Micro, Haut-Parleur
-Fournisseur 3 : Nappa
Composants : CPU, Roue, Hélice
-Fournisseur 4 : KIA
Composants : Bras, Écran, Caméra, Haut-Parleur, CPU
-Fournisseur 5 : FORD
Composants : Hélice, CPU, Écran, Bras, Roue

ACTIVITÉS ET TÂCHES : Il y a 6 activités initiales et chacune d'entre elles contient une liste de tâches.
-Activité 1 : Menage chez Jordan (Non débutée)
Tâche : Balayeuse
Actions : Avancer (Roue), Aspirer (Bras)
-Activité 2 : Chasse au trésor (Non débutée)
Tâche : Chercher
Actions : Avancer (Roue), Analyser (Caméra), Creuser (Bras)
-Activité 3 : Captation Ultimate Frisbee (Non débutée)
Tâches :
Camera Drone : Voler (Hélice), Enregistrer (Caméra)
Camera Ground : Avancer (Roue), Enregistrer (Caméra)
Enregistrer Son : Enregistrer (Micro)
-Activité 4 : Revision Examen (Terminée)
Tâche : Poser une question
Actions : Question Visuelle (Écran), Question Audio (Haut-Parleur)
-Activité 5 : Course Robots (En cours)
Tâche : Rouler
Action : Rouler (Roue)
-Activité 6 : Ateliers mecanique (Non débutée)
Tâche : Réparation
Action : Support Visuel (CPU, Roue, Bras, Haut-Parleur, Écran, Hélice)

- INSCRIPTION AUX ACTIVITÉS :
Les clients 1 à 10 sont inscrits à toutes les activités (activités 1 à 6).


>> INSTRUCTIONS POUR INSTALLER LE PROJET
Prérequis :
Java 11 et Apache Maven 3.6 (ou une version ultérieure)

1. Téléchargement des librairies externes :
Les dépendances sont gérées par Maven et spécifiées dans le fichier pom.xml.

2. Cloner le projet :
git clone https://github.com/JojoBoulais/Codesniffers123.git
cd Codesniffers123/application

3. Compiler et créer le jar :
mvn clean install


>> INSTRUCTIONS POUR EXÉCUTER L'APPLICATION
1. Parcourir les fichiers :
Se rendre à l'emplacement 'application/src/main/java'

2. Exécuter le .jar :
java -jar target/robotix.jar
