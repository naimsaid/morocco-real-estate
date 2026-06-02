# Docker - Backend Morocco Real Estate

## Démarrage rapide

```bash
# Construire et démarrer tous les services
docker-compose up --build

# En arrière-plan
docker-compose up -d --build
```

## Services

| Service | Port | Description |
|---------|------|-------------|
| backend | 8080 | API Spring Boot |
| postgres | 5432 | Base de données PostgreSQL |

## Variables d'environnement

Crée un fichier `.env` à la racine pour personnaliser :

```env
JWT_SECRET=ton_secret_jwt
CORS_ALLOWED_ORIGINS=http://localhost:4200,http://localhost:3000
```

## Commandes utiles

```bash
# Voir les logs
docker-compose logs -f backend

# Arrêter
docker-compose down

# Arrêter et supprimer les volumes (données)
docker-compose down -v

# Rebuild uniquement le backend
docker-compose up -d --build backend
```

## Build manuel (sans docker-compose)

```bash
cd backend
docker build -t morocco-realestate-backend .
docker run -p 8080:8080 \
  -e SPRING_DATASOURCE_URL=jdbc:postgresql://host.docker.internal:5432/morocco_real_estate \
  morocco-realestate-backend
```
