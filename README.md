# Mise en place d'une API Restful avec Laravel 7.x

## Pourquoi Laravel ?

> Laravel nous met à disposition des outils puissant pour faire une API sécurisé, performante, mais surtout accessible au plus grand nombre avec des outils intuitif et une documentation complète. Nous utiliserons le package Passport qui nous fournira un système d'authentification sécurisée avec OAuth2.


## Prérequis
* Serveur Apache2
* PHP
* MySQL

## Tutoriel

> Nous allons désormais à partir d'un projet existant disponible sur un dépo git, vous donnez une solution clé en main pour avoir une api fonctionelle et sécurisée.

##### 1. La première chose à faire serait de cloner le dépo que je vais vous fournir à l'adresse ci-dessous

[Gitlab Repository](https://rendu-git.etna-alternance.net/module-6590/activity-37142/group-772041)

> Désormais vous vous retrouvez avec un projet auquel ils nous manque le dossier /vendor de laravel et le fichier .env

##### 2. Maintenant à coté vous allez créer un projet Laravel neuf, si ce n'est pas déjà fait, installer Laravel sur votre machine à l'aide de cette commande.

    composer global require laravel/installer

##### 3. Créer un nouveau projet

    composer create-project --prefer-dist laravel/laravel blog

##### 4. Déplacer le dossier /vendor et le fichier .env dans le dossier /RestaurantAdvisor du repository cloner au préalable.

##### 5. En fonction du serveur que vous utiliser, crée une database et lié le à votre fichier .env, changer le nom de la database en fonction de celle que vous avez crée, et le port dans le cas où ce n'est pas le meme.

##### 6. Déplacer vous dans le dossier /RestaurantAdvisor et lancer la commande qui permettra a composer d'installer Passport

    composer require laravel/passport

##### 7. Effectuer une migration, nous avons inclus des seeds pour peupler la database

    php artisan migrate:fresh --seed

##### 8. Une fois cela fais, nous pouvons installer Passport sur notre projet actuelle, toujours dans le dossier /RestaurantAdvisor lancer la commande 

    php artisan passport:install

> Le retour de cette commande vous fourni un couple de clé que Laravel utilisera pour confirmer l'authencité de l'application, si vous etes amené à refaire des migrations, Laravel vous notifiera d'une erreur, il suffira de regenerer ces clé avec la commande

    php artisan passport:client --personal --name=RestaurantAdvisorAPI

##### 9. Vous avez maintenant une API RESTful avec Laravel 7.x fonctionelle et sécurisé avec un système d'authentification par token.
##### N'oubliez pas que Laravel distingue ces routes entre les web routes et les api routes, donc dans notre cas, vous devez rajoutez /api/ avant la route que vous voulez utiliser, par exemple pour vous inscrire, utiliser la route /api/register

![Screenshot Of Route](https://i.ibb.co/JnCpLxG/Screenshot-12.jpg)

## Explication
> Certaine de nos routes sont accessible sans avoir à ce connecter comme par exemple pour la route
'/restaurant/{id}/menus', '/restaurants', '/auth' et '/register'

##### 1. Pour vous inscrire, vous pouvez utiliser la route '/register', une fois que vous etes inscrit, l'api vous fournit un token que vous devrez garder pour vous authentifier sur les routes sécurisée. (La route /auth vous fournit aussi un nouveau token)

![Screenshot Of Token](https://image.noelshack.com/fichiers/2020/13/4/1585221459-screenshot-3.jpg)

##### 2. Maintenant que vous avez ce token, pour accedez aux routes sécurisé vous devez l'inclure dans les headers de vos requetes, à l'aide de PostMan par exemple vous pouvez directement inclure le token dans le header de cette manière. Le format est "Authorization : Bearer $accessToken"

![Screenshot Of Header](https://i.ibb.co/Qfc5g9F/Screenshot-8.jpg)

##### 3. Vous avez maintenant grace à ce header, accès aux routes sécurisée de votre API, vous pouvez les configuer dans le fichier routes/api.php de Laravel car ils sont présent dans le middleware qui permet la vérification de ce token.

![Screenshot Of Header](https://i.ibb.co/hCVFrb3/Screenshot-9.jpg)

## Problèmes ?

##### Vous pouvez etre amener à avoir des erreur avec Laravel qui stipule que vous avez des controllers ou classes introuvables, des erreurs de fichier inexistant, nous vous invitons à effectuer la commande ci-dessous pour pallier à certains de ces problèmes, cette commande permet à composer de réindexer les fichiers du projet, en regénérant la liste de classes dont le projet à besoin.

    composer dumpautoload

## Bonus

##### N'hésitez pas à configurer vos fichier hosts et vos virtuals hosts pour avoir un nom de domaine personalisable en local

![Screenshot Of Header](https://i.ibb.co/gDhSk94/Screenshot-11.jpg)

> Sa fais sexy et sa mange pas de pain


# Mise en place d'une aplication Android avec Android Studio

## Packages utilisés
* Gogle material
* Cardview
* Retrofit
* Butterknife
* Awesome validation

## Déroulement du développement et fonctionnalités
* Création des différentes activité, dont une pour retrobuild avec une interface qui nous permet de gerer les calls directement dedans
* Système de stockage de l'access token avec les sharedpreferences
* Sécurisation des vues, en fonction de la réponse de l'api, si la personne est "Unauthenticate" elle ce retrouve sur la page login
* Création des vues de login, register et de restaurants (afficher les restaurants)
* Interconnexion entre ces vues grace à butterknife. (@OnClick)

## Résultat
* Système d'authentification avec token fonctionnelle (accès aux routes sécurisé)
* Vue login
* Vue register
* Vue Restaurant
