# Framework

## Configuration du web.xml
<servlet> 
    <servlet-name>FrontServlet</servlet-name> 
    <servlet-class>etu1877.framework.servlet.FrontServlet</servlet-class> 
</servlet>
<servlet-mapping> 
    <servlet-name>FrontServlet</servlet-name> 
    <url-pattern>/</url-pattern> 
</servlet-mapping> 

## Class ModelView 
Cette classe est utilisée pour definir des urls, des données qui sont ensuite envoyer a un view.
Les arguments utilisés sont de types :
    - String url;
    - HashMap<String,Object> donnee;

## Class Scope
Cette class permet de créer une annotation a une autre classe. Autrement dit, on peut définir le type d'une classe grâce à celle-ci

## Class Url_annotation
Comme pour la class Scope, elle permet de créer des annotations mais cette fois-ci pour des fonctions.
L'annotation créée sur les fonctions est l'url pour les transferts des données

## Class Utils
C'est une Class utlisée pour stocker des fonctions nécéssaires

## Class FileUpload
Class utilisée pour avoir le nom et les methods dans une autre class

## Class Emp
Une class permettant de créer les urls grâce aux annotations et pour recupérer les données d'un formulaire pour les envoyées vers une view

## Class FileUpload
Une class permettant de créer les urls grâce aux annotations et pour recupérer les données d'un formulaire qui peut faire des uploads de fichier pour les envoyées vers une view

## Script.bat
Script premettant de créer un fichier .jar pour tout les fichier .class du dossier Framework mais également de créer un fichier .war pour le Test de Framework