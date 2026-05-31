# Morocco Real Estate Platform

Une plateforme complète de vente et achat immobilier en ligne pour tout le Maroc, inspirée des meilleurs sites immobiliers américains comme Zillow et Redfin.

## Technologies

### Backend
- **Spring Boot 3.2.0** - Framework Java
- **PostgreSQL** - Base de données relationnelle
- **Spring Security** - Sécurité et authentification
- **JWT (JSON Web Tokens)** - Authentification stateless
- **Spring Data JPA** - ORM et accès aux données
- **Maven** - Gestion des dépendances

### Frontend
- **Angular 17** - Framework frontend
- **Angular Material** - Composants UI Material Design
- **RxJS** - Programmation réactive
- **ngx-toastr** - Notifications toast
- **TypeScript** - Typage statique

## Fonctionnalités

### Pour les utilisateurs
- **Recherche avancée** de propriétés avec filtres (type, prix, ville, région, chambres, etc.)
- **Affichage détaillé** des propriétés avec photos, description, équipements
- **Système de favoris** pour sauvegarder les propriétés préférées
- **Authentification** sécurisée avec JWT
- **Tableau de bord** personnel pour gérer ses propriétés et favoris

### Pour les agents/propriétaires
- **Publication d'annonces** avec formulaire complet
- **Gestion des propriétés** (ajout, modification, suppression)
- **Mise en vedette** des propriétés
- **Statistiques** sur les propriétés publiées

### Fonctionnalités techniques
- **API RESTful** complète
- **Sécurité** avec Spring Security et JWT
- **Pagination** des résultats
- **Validation** des données
- **Design responsive** pour mobile et desktop
- **Architecture modulaire** et maintenable

## Structure du projet

```
morocco-real-estate/
├── backend/                 # Application Spring Boot
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   └── com/realestate/morocco/
│   │   │   │       ├── entity/          # Entités JPA
│   │   │   │       ├── repository/      # Repositories Spring Data
│   │   │   │       ├── service/         # Services métier
│   │   │   │       ├── controller/      # Contrôleurs REST
│   │   │   │       ├── dto/             # Data Transfer Objects
│   │   │   │       ├── security/        # Configuration sécurité
│   │   │   │       └── MoroccoRealEstateApplication.java
│   │   │   └── resources/
│   │   │       └── application.properties
│   │   └── test/
│   └── pom.xml
└── frontend/                # Application Angular
    ├── src/
    │   ├── app/
    │   │   ├── components/    # Composants Angular
    │   │   ├── services/      # Services HTTP
    │   │   ├── models/        # Modèles TypeScript
    │   │   ├── guards/        # Guards de route
    │   │   ├── shared/        # Composants partagés
    │   │   ├── app.module.ts
    │   │   └── app-routing.module.ts
    │   ├── assets/
    │   ├── index.html
    │   └── styles.css
    ├── package.json
    ├── angular.json
    └── tsconfig.json
```

## Prérequis

- **Java 17** ou supérieur
- **Maven 3.6+**
- **Node.js 18+** et **npm 9+**
- **PostgreSQL 14+**
- **IDE** (IntelliJ IDEA, VS Code, Eclipse)

## Installation et Configuration

### 1. Configuration de la base de données PostgreSQL

```sql
-- Créer la base de données
CREATE DATABASE morocco_real_estate;

-- Créer un utilisateur (optionnel)
CREATE USER morocco_user WITH PASSWORD 'your_password';
GRANT ALL PRIVILEGES ON DATABASE morocco_real_estate TO morocco_user;
```

### 2. Configuration du Backend

```bash
# Naviguer vers le dossier backend
cd backend

# Modifier application.properties avec vos credentials PostgreSQL
# src/main/resources/application.properties
```

Modifier les paramètres de connexion dans `application.properties`:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/morocco_real_estate
spring.datasource.username=postgres
spring.datasource.password=votre_mot_de_passe
```

```bash
# Compiler et lancer le backend
mvn clean install
mvn spring-boot:run
```

Le backend sera accessible sur `http://localhost:8080`

### 3. Configuration du Frontend

```bash
# Naviguer vers le dossier frontend
cd frontend

# Installer les dépendances
npm install

# Lancer le serveur de développement
npm start
```

Le frontend sera accessible sur `http://localhost:4200`

### 4. Configuration CORS

L'application est configurée pour autoriser les requêtes depuis `http://localhost:4200`. Si vous utilisez un autre port, modifiez la configuration CORS dans:
- Backend: `SecurityConfig.java`
- Frontend: `PropertyService`, `AuthService`, `FavoriteService`

## API Endpoints

### Authentification
- `POST /api/auth/register` - Inscription d'un nouvel utilisateur
- `POST /api/auth/login` - Connexion utilisateur

### Propriétés
- `GET /api/properties` - Liste de toutes les propriétés
- `GET /api/properties/{id}` - Détails d'une propriété
- `GET /api/properties/featured` - Propriétés en vedette
- `POST /api/properties/search` - Recherche avancée
- `POST /api/properties` - Créer une propriété (authentifié)
- `PUT /api/properties/{id}` - Modifier une propriété (authentifié)
- `DELETE /api/properties/{id}` - Supprimer une propriété (authentifié)
- `GET /api/properties/my-properties` - Mes propriétés (authentifié)

### Favoris
- `GET /api/favorites` - Liste des favoris (authentifié)
- `POST /api/favorites/{propertyId}` - Ajouter aux favoris (authentifié)
- `DELETE /api/favorites/{propertyId}` - Retirer des favoris (authentifié)
- `GET /api/favorites/check/{propertyId}` - Vérifier si favori (authentifié)

### Utilisateurs
- `GET /api/users/me` - Informations utilisateur actuel (authentifié)
- `GET /api/users/{id}` - Informations utilisateur par ID
- `PUT /api/users/{id}` - Modifier profil utilisateur (authentifié)

## Utilisation

### Inscription
1. Cliquez sur "S'inscrire" dans le menu
2. Remplissez le formulaire d'inscription
3. Choisissez votre type de compte (Utilisateur ou Agent immobilier)
4. Validez l'inscription

### Publication d'une annonce
1. Connectez-vous à votre compte
2. Cliquez sur "Publier une annonce" dans le menu
3. Remplissez le formulaire avec les détails de la propriété
4. Ajoutez des équipements optionnels
5. Publiez l'annonce

### Recherche de propriétés
1. Utilisez la barre de recherche rapide sur la page d'accueil
2. Ou accédez à la page "Rechercher" pour des filtres avancés
3. Filtrez par type de bien, prix, ville, chambres, etc.
4. Consultez les résultats

### Gestion des favoris
1. Connectez-vous à votre compte
2. Cliquez sur le cœur sur une propriété pour l'ajouter aux favoris
3. Accédez à "Mes favoris" dans le menu pour voir vos propriétés sauvegardées

## Déploiement

### Backend (Production)
```bash
# Compiler le JAR
mvn clean package -DskipTests

# Le JAR sera dans target/morocco-real-estate-1.0.0.jar
# Lancer avec: java -jar target/morocco-real-estate-1.0.0.jar
```

### Frontend (Production)
```bash
# Compiler pour production
ng build --prod

# Les fichiers statiques seront dans dist/morocco-real-estate/
# Déployez-les sur un serveur web (Nginx, Apache, etc.)
```

## Sécurité

- **Mot de passe** haché avec BCrypt
- **JWT** pour l'authentification stateless
- **CORS** configuré pour le frontend
- **Validation** des entrées utilisateur
- **Protection** contre CSRF activée

## Personnalisation

### Changer le thème
Modifiez les variables CSS dans `src/styles.css`:
```css
:root {
  --primary-color: #0066cc;
  --secondary-color: #ff6b35;
  --accent-color: #00c853;
  /* ... */
}
```

### Ajouter de nouvelles villes
Modifiez les tableaux dans `search.component.ts`:
```typescript
cities = ['Casablanca', 'Rabat', 'Marrakech', /* ... */];
regions = ['Casablanca-Settat', 'Rabat-Salé-Kénitra', /* ... */];
```

## Support

Pour toute question ou problème, veuillez contacter l'équipe de développement.

## Licence

Ce projet est fourni à des fins éducatives et de démonstration.

## Roadmap

- [ ] Intégration de Google Maps
- [ ] Système de messagerie interne
- [ ] Notifications push
- [ ] Gestion des images avec Cloudinary
- [ ] Système de reviews et notes
- [ ] Comparaison de propriétés
- [ ] Alertes de prix
- [ ] Application mobile (React Native)
