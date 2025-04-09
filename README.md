# CleanRMAPI

CleanRMAPI est une application multiplateforme Kotlin visant à afficher et gérer des données récupérées via l’API Rick & Morty. Ce projet utilise une approche de Clean Architecture pour séparer les couches Domain, Data et UI, ainsi qu’une logique partagée entre Android et Desktop. L’interface utilisateur est implémentée en Compose Multiplatform.

## Table des matières

- [Architecture](#architecture)
- [Technologies](#technologies)
- [Structure du projet](#structure-du-projet)
- [Installation et Exécution](#installation-et-exécution)

## Architecture

CleanRMAPI a été conçu en adoptant une architecture en couches respectant les principes de Clean Architecture. Le projet est organisé de la façon suivante :

### Domain
Contient les modèles de données et la logique métier partagée : modèles pour les personnages, épisodes, locations.

### Data
Gère la récupération et la persistance des données.

- **Remote** : Utilise Ktor pour interroger l’API Rick & Morty et kotlinx.serialization pour la conversion des données JSON.
- **Local** : Implémente une persistance locale via Room et fournit des DAOs pour accéder aux données stockées (CharacterDAO, EpisodeDAO, LocationDAO).
- **Mappers** : Les fonctions de mapping convertissent les données entre les modèles de données distants, les entités Room et les modèles Domain.

### UI
Implémentée en Compose Multiplatform, elle offre une interface utilisateur sur Android et Desktop.

- **ViewModels** : Définis en code commun (avec expect/actual ou via des frameworks tels que KMP-ObservableViewModel) et gérés par Koin pour l’injection de dépendances.
- **Navigation** : Utilise Compose Navigation et une classe Destination pour orchestrer la navigation entre les écrans (characters, character details, episode details, location details).

### Media
La gestion des ressources audio (sound effects) est implémentée de manière multiplateforme en utilisant expect/actual. Sur Android, ExoPlayer est utilisé, et sur Desktop, l’API Java Sound est exploitée pour jouer des sons stockés dans composeResources/files.

> **Note :** Cette architecture favorise la réutilisation du code et la séparation des préoccupations, facilitant ainsi la maintenance et l’évolution du projet.

## Technologies

Le projet utilise les bibliothèques et frameworks suivants :

- **Kotlin Multiplatform**  
  Partage du code métier et de l’UI entre Android et Desktop.

- **Jetpack Compose / Compose Multiplatform**  
  Création d’interfaces utilisateurs modernes et réactives.

- **Ktor**  
  Gestion des requêtes réseau et de la communication avec l’API.

- **kotlinx.serialization**  
  Sérialisation/désérialisation JSON.

- **Koin**  
  Injection de dépendances pour une gestion modulaire et testable.

- **Room**  
  Persistance locale sur Android à l’aide d’un DAO/Entity.

Ces technologies offrent une base solide pour construire une application multiplateforme robuste et évolutive.

## Structure du projet

La structure du projet est organisée de manière à respecter la Clean Architecture :

```bash
CleanRMAPI/
├── shared/
│   ├── src/
│   │   ├── commonMain/
│   │   │   ├── domain       # Modèles Domain et logique métier partagée
│   │   │   ├── data         # Accès aux données (Remote, Local, Mappers)
│   │   │   ├── media        # Déclaration expect pour la lecture audio
│   │   │   └── ui           # ViewModels et composables partagés
│   │   ├── androidMain/
│   │   │   ├── media        # Implémentation AudioPlayer pour Android
│   │   │   
│   │   └── desktopMain/
│   │       ├── media        # Implémentation AudioPlayer pour Desktop
│   │   
├── build.gradle.kts
└── README.md
```
## Modules Koin

Les modules de dépendances **Koin** se trouvent dans `shared/src/commonMain/kotlin/org/mathieu/cleanrmapi/data`  
(`remoteModule`, `repositoriesModule`, `databaseModule`, etc.), et le module `mediaModule` gère l’injection de l’`AudioPlayer`.

---

## Installation et Exécution

### Cloner le dépôt

```bash
git clone https://github.com/votre-utilisateur/CleanRMAPI.git
cd CleanRMAPI
```

Faites pas comme moi si vous voulez fork et désélectionnez l'option : 

Copy the versions/4_kmp_every_platforms branch only

Promis vous allez gagner du temps ... :)



