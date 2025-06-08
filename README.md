# easygestion-app

Application web simple de gestion des produits et des commandes, développée avec Angular 19 et Spring Boot 3 (Java 17), utilisant MySQL comme base de données.

## Description

Cette application permet :

- Une **page de connexion** (avec des identifiants codés en dur pour le moment).
- Un **tableau de bord** offrant :
  - La **gestion des produits** (opérations CRUD)
  - La **gestion des commandes** (opérations CRUD)
- Un **tableau dynamique** qui affiche soit la liste des produits, soit la liste des commandes selon la section sélectionnée.
- Une communication avec une **API REST** construite avec Spring Boot, connectée à une base de données **MySQL**.

## Technologies utilisées

- **Front-end** : Angular 19, TypeScript, HTML, CSS  
- **Back-end** : Java 17, Spring Boot 3  
- **Base de données** : MySQL

## Principes et bonnes pratiques appliqués

### 1. Séparation des responsabilités
Séparation claire entre le front-end (interface utilisateur) et le back-end (logique métier et base de données).

### 2. Architecture RESTful
Utilisation d’une API REST bien structurée avec les méthodes HTTP standard (`GET`, `POST`, `PUT`, `DELETE`).

### 3. Architecture orientée composants
Le front-end Angular est organisé autour de composants.

### 4. Modèle MVC (Spring Boot)
Le back-end est structuré selon le modèle MVC : Contrôleurs, Services, Repositories, Entités.

### 5. CRUD complet
L’application permet la création, la lecture, la mise à jour et la suppression de produits et de commandes.

### 6. Intégration base de données
Connexion avec MySQL via Spring Data JPA, avec mapping des entités.

### 7. Gestion des erreurs
Gestion simple des erreurs pour éviter les crashs et informer l’utilisateur.

### 8. Expérience utilisateur
Des **messages de confirmation** et **messages d’erreur** sont affichés pour informer l’utilisateur après chaque action (ajout, modification, suppression...). Cela améliore l’interaction et la clarté lors de l’utilisation de l’application.


