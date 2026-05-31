-- Seed sample data only when each table is empty (non-destructive across restarts)

-- Insert sample users
INSERT INTO users (email, password, first_name, last_name, phone, role, enabled, account_non_expired, account_non_locked, credentials_non_expired, created_at, updated_at)
SELECT * FROM (VALUES 
  ('agent@marocimmobilier.ma', '$2a$10$7xulfC9ONvmZeGc2vWcPYO5H4Lcczd2ejLlE3NGyfdAv7tynYZ7dy', 'Ahmed', 'Benali', '0612345678', 'AGENT', true, true, true, true, NOW(), NOW()),
  ('user1@example.com', '$2a$10$7xulfC9ONvmZeGc2vWcPYO5H4Lcczd2ejLlE3NGyfdAv7tynYZ7dy', 'Fatima', 'Zahraoui', '0623456789', 'USER', true, true, true, true, NOW(), NOW()),
  ('user2@example.com', '$2a$10$7xulfC9ONvmZeGc2vWcPYO5H4Lcczd2ejLlE3NGyfdAv7tynYZ7dy', 'Mohammed', 'Alami', '0634567890', 'USER', true, true, true, true, NOW(), NOW())
) AS v(email, password, first_name, last_name, phone, role, enabled, account_non_expired, account_non_locked, credentials_non_expired, created_at, updated_at)
WHERE NOT EXISTS (SELECT 1 FROM users);

-- Ensure sample accounts always have a valid, known password (password123)
UPDATE users
SET password = '$2a$10$7xulfC9ONvmZeGc2vWcPYO5H4Lcczd2ejLlE3NGyfdAv7tynYZ7dy', enabled = true
WHERE email IN ('agent@marocimmobilier.ma', 'user1@example.com', 'user2@example.com');

-- Insert sample properties
INSERT INTO properties (title, description, property_type, listing_type, price, area, bedrooms, bathrooms, floors, address, city, region, latitude, longitude, featured, available, owner_id, created_at, updated_at)
SELECT * FROM (VALUES 
  ('Villa de luxe avec piscine à Casablanca', 'Magnifique villa moderne avec piscine privée, jardin et vue sur mer. Située dans le quartier résidentiel d''Anfa Supérieur.', 'VILLA', 'SALE', 8500000, 450, 5, 4, 2, '45 Boulevard Ziraoui, Anfa Supérieur', 'Casablanca', 'Casablanca-Settat', 33.5731, -7.5898, true, true, 1, NOW(), NOW()),
  
  ('Appartement moderne au centre-ville', 'Bel appartement de 3 chambres avec balcon et vue sur la ville. Proche des commerces et transports.', 'APARTMENT', 'SALE', 1200000, 110, 3, 2, 5, '78 Rue Mohammed V, Centre-ville', 'Casablanca', 'Casablanca-Settat', 33.5731, -7.5898, true, true, 1, NOW(), NOW()),
  
  ('Studio lumineux à Maarif', 'Studio rénové avec cuisine équipée et salle de bain moderne. Idéal pour étudiants ou jeunes professionnels.', 'APARTMENT', 'RENT', 4500, 35, 1, 1, 3, '23 Rue Ibnou Zohr, Maarif', 'Casablanca', 'Casablanca-Settat', 33.5850, -7.6200, false, true, 1, NOW(), NOW()),
  
  ('Riad traditionnel à Marrakech', 'Authentique riad marocain avec patio, jardin et terrasse. Situé dans la médina proche de la place Jemaa el-Fna.', 'RIAD', 'SALE', 3200000, 280, 4, 3, 1, '12 Rue Bab Agnaou, Médina', 'Marrakech', 'Marrakech-Safi', 31.6258, -7.9891, true, true, 1, NOW(), NOW()),
  
  ('Penthouse avec vue panoramique à Rabat', 'Pentague luxueux avec terrasse offrant une vue panoramique sur l''océan et la ville. Quartier Hassan.', 'APARTMENT', 'SALE', 4200000, 180, 4, 3, 8, '15 Avenue Hassan II, Hassan', 'Rabat', 'Rabat-Salé-Kénitra', 34.0209, -6.8416, true, true, 1, NOW(), NOW()),
  
  ('Bureau moderne à Tanger', 'Espace de bureau de 100m² dans immeuble moderne avec parking et sécurité. Proche du port.', 'COMMERCIAL', 'RENT', 8000, 100, 0, 2, 1, '56 Boulevard Pasteur, Centre-ville', 'Tanger', 'Tanger-Tétouan-Al Hoceïma', 35.7595, -5.8340, false, true, 1, NOW(), NOW()),
  
  ('Maison avec jardin à Fès', 'Maison familiale avec grand jardin, garage et terrasse. Quartier résidentiel calme.', 'HOUSE', 'SALE', 1800000, 200, 4, 2, 1, '89 Avenue Hassan II, Ville Nouvelle', 'Fès', 'Fès-Meknès', 34.0331, -5.0003, false, true, 1, NOW(), NOW()),
  
  ('Appartement vue mer à Agadir', 'Appartement de 2 chambres avec vue imprenable sur l''océan. Proche de la plage.', 'APARTMENT', 'SALE', 950000, 85, 2, 1, 4, '34 Boulevard du 20 Août, Centre', 'Agadir', 'Souss-Massa', 30.4278, -9.5981, true, true, 1, NOW(), NOW()),
  
  ('Terrain constructible à Meknès', 'Terrain de 500m² constructible dans quartier en développement. Tous réseaux disponibles.', 'LAND', 'SALE', 450000, 500, 0, 0, 0, '123 Route d''El Hajeb, Hamria', 'Meknès', 'Fès-Meknès', 33.8935, -5.5473, false, true, 1, NOW(), NOW()),
  
  ('Local commercial à Oujda', 'Local commercial de 60m² en rez-de-chaussée avec vitrine. Passage fréquenté.', 'COMMERCIAL', 'RENT', 5000, 60, 0, 1, 0, '12 Avenue Mohammed V, Centre', 'Oujda', 'L''Oriental', 34.6867, -1.9114, false, true, 1, NOW(), NOW()),
  
  ('Villa contemporaine à Tétouan', 'Villa moderne avec architecture contemporaine, piscine et jardin. Vue sur la montagne.', 'VILLA', 'SALE', 3800000, 320, 5, 4, 2, '45 Route Martil, Martil', 'Tétouan', 'Tanger-Tétouan-Al Hoceïma', 35.5889, -5.3626, false, true, 1, NOW(), NOW()),
  
  ('Appartement atypique à Essaouira', 'Appartement de caractère dans médina avec terrasse et vue sur la mer.', 'APARTMENT', 'RENT', 5500, 75, 2, 1, 2, '8 Rue Skala, Médina', 'Essaouira', 'Marrakech-Safi', 31.5085, -9.7595, false, true, 1, NOW(), NOW())
) AS v(title, description, property_type, listing_type, price, area, bedrooms, bathrooms, floors, address, city, region, latitude, longitude, featured, available, owner_id, created_at, updated_at)
WHERE NOT EXISTS (SELECT 1 FROM properties);

-- Insert sample property images
INSERT INTO property_images (property_id, image_url, display_order)
SELECT * FROM (VALUES 
  (1, 'https://images.unsplash.com/photo-1613490493576-7fde63acd811?w=800', 1),
  (1, 'https://images.unsplash.com/photo-1600596542815-ffad4c1539a9?w=800', 2),
  (1, 'https://images.unsplash.com/photo-1600607687939-ce8a6c25118c?w=800', 3),
  
  (2, 'https://images.unsplash.com/photo-1502672260266-1c1ef2d93688?w=800', 1),
  (2, 'https://images.unsplash.com/photo-1560448204-e02f11c3d0e2?w=800', 2),
  
  (3, 'https://images.unsplash.com/photo-1522708323590-d24dbb6b0267?w=800', 1),
  
  (4, 'https://images.unsplash.com/photo-1534351590666-13e3e96b5017?w=800', 1),
  (4, 'https://images.unsplash.com/photo-1595576508898-0ad5c879a061?w=800', 2),
  (4, 'https://images.unsplash.com/photo-1580587771525-78b9dba3b914?w=800', 3),
  
  (5, 'https://images.unsplash.com/photo-1512917774080-9991f1c4c750?w=800', 1),
  (5, 'https://images.unsplash.com/photo-1600585154340-be6161a56a0c?w=800', 2),
  
  (6, 'https://images.unsplash.com/photo-1497366216548-37526070297c?w=800', 1),
  
  (7, 'https://images.unsplash.com/photo-1600585154526-990dced4db0d?w=800', 1),
  (7, 'https://images.unsplash.com/photo-1600566753190-17f0baa2a6c3?w=800', 2),
  
  (8, 'https://images.unsplash.com/photo-1600573472550-8090b5e0745e?w=800', 1),
  (8, 'https://images.unsplash.com/photo-1600047509807-ba8f99d2cdde?w=800', 2),
  
  (9, 'https://images.unsplash.com/photo-1500382017468-9049fed747ef?w=800', 1),
  
  (10, 'https://images.unsplash.com/photo-1497366754035-f200968a6e72?w=800', 1),
  
  (11, 'https://images.unsplash.com/photo-1600607687644-c7171b42498f?w=800', 1),
  (11, 'https://images.unsplash.com/photo-1600566753086-00f18fb6b3ea?w=800', 2),
  
  (12, 'https://images.unsplash.com/photo-1600585154340-be6161a56a0c?w=800', 1)
) AS v(property_id, image_url, display_order)
WHERE NOT EXISTS (SELECT 1 FROM property_images);

-- Insert sample amenities for properties
INSERT INTO property_amenities (property_id, amenity)
SELECT * FROM (VALUES 
  (1, 'Piscine'),
  (1, 'Jardin'),
  (1, 'Garage'),
  (1, 'Sécurité 24/7'),
  (1, 'Vue mer'),
  
  (2, 'Balcon'),
  (2, 'Ascenseur'),
  (2, 'Parking'),
  
  (3, 'Cuisine équipée'),
  (3, 'Chauffage central'),
  
  (4, 'Patio'),
  (4, 'Jardin'),
  (4, 'Terrasse'),
  (4, 'Climatisation'),
  
  (5, 'Terrasse'),
  (5, 'Vue océan'),
  (5, 'Ascenseur'),
  (5, 'Parking'),
  
  (6, 'Parking'),
  (6, 'Sécurité'),
  (6, 'Climatisation'),
  
  (7, 'Jardin'),
  (7, 'Garage'),
  (7, 'Terrasse'),
  
  (8, 'Vue mer'),
  (8, 'Balcon'),
  (8, 'Ascenseur'),
  
  (9, 'Réseaux disponibles'),
  (9, 'Clôturé'),
  
  (10, 'Vitrine'),
  (10, 'Climatisation'),
  
  (11, 'Piscine'),
  (11, 'Jardin'),
  (11, 'Vue montagne'),
  (11, 'Garage'),
  
  (12, 'Terrasse'),
  (12, 'Vue mer')
) AS v(property_id, amenity)
WHERE NOT EXISTS (SELECT 1 FROM property_amenities);

-- Insert sample favorites
INSERT INTO favorites (user_id, property_id, created_at)
SELECT * FROM (VALUES
  (2, 1, NOW()),
  (2, 4, NOW()),
  (2, 5, NOW()),
  (3, 2, NOW()),
  (3, 8, NOW())
) AS v(user_id, property_id, created_at)
WHERE NOT EXISTS (SELECT 1 FROM favorites);
