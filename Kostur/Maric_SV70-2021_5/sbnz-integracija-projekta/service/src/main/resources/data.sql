-- Brisanje postojećih podataka da bi se izbegli duplikati pri svakom pokretanju
DELETE FROM mineral_hierarchy;
DELETE FROM seasonal_tip;

DELETE FROM mineral;
--DELETE FROM users;
--DELETE FROM notification;

--INSERT INTO public.users(id, email, password, role) VALUES
    --(gen_random_uuid(), 'admin@mineral.com', '$2a$10$YcGSblI4aWfPhuXkaVPpDueqouKYSwnjXiv8ZAJJNRFliO1gxHChS', 'ADMIN');


-- Ubacivanje podataka u tabelu MINERAL koristeći navedeni templejt
INSERT INTO public.mineral(id, accessibility, colors, difficulty, hardness_max, hardness_min, locations, luster, name, rock_types, streak_color, transparency) VALUES
    (gen_random_uuid(), 'ON_FOOT', '["green", "black"]', 'MEDIUM', 4.0, 2.5, '["Zlatibor", "Kopaonik"]', 'GREASY', 'Serpentine', '["serpentinite"]', 'white', 'TRANSLUCENT');

INSERT INTO public.mineral(id, accessibility, colors, difficulty, hardness_max, hardness_min, locations, luster, name, rock_types, streak_color, transparency) VALUES
    (gen_random_uuid(), 'BY_CAR', '["green"]', 'MEDIUM', 4.0, 3.5, '["Rudnik"]', 'GLASSY', 'Malachite', '["limestone"]', 'green', 'OPAQUE');

INSERT INTO public.mineral(id, accessibility, colors, difficulty, hardness_max, hardness_min, locations, luster, name, rock_types, streak_color, transparency) VALUES
    (gen_random_uuid(), 'BY_CAR', '["purple"]', 'HARD', 7.0, 7.0, '["Fruška gora"]', 'GLASSY', 'Amethyst (Quartz)', '["volcanic rock"]', 'white', 'TRANSPARENT');

INSERT INTO public.mineral(id, accessibility, colors, difficulty, hardness_max, hardness_min, locations, luster, name, rock_types, streak_color, transparency) VALUES
    (gen_random_uuid(), 'BY_CAR', '["colorless"]', 'MEDIUM', 7.0, 7.0, '["Kopaonik", "Fruška gora"]', 'GLASSY', 'Rock Crystal (Quartz)', '["granite", "pegmatite"]', 'white', 'TRANSPARENT');

INSERT INTO public.mineral(id, accessibility, colors, difficulty, hardness_max, hardness_min, locations, luster, name, rock_types, streak_color, transparency) VALUES
    (gen_random_uuid(), 'ON_FOOT', '["white", "grey"]', 'MEDIUM', 4.5, 3.5, '["Zlatibor"]', 'DULL', 'Magnezit', '["serpentinite"]', 'white', 'OPAQUE');

INSERT INTO public.mineral(id, accessibility, colors, difficulty, hardness_max, hardness_min, locations, luster, name, rock_types, streak_color, transparency) VALUES
    (gen_random_uuid(), 'ON_FOOT', '["black"]', 'HARD', 5.5, 5.5, '["Zlatibor"]', 'METALLIC', 'Hromit', '["serpentinite"]', 'brown', 'OPAQUE');


-- Ubacivanje podataka u tabelu MINERAL_HIERARCHY koristeći navedeni templejt
INSERT INTO public.mineral_hierarchy(id, sub_type, super_type) VALUES
    (gen_random_uuid(), 'Amethyst (Quartz)', 'Kvarc');

INSERT INTO public.mineral_hierarchy(id, sub_type, super_type) VALUES
    (gen_random_uuid(), 'Rock Crystal (Quartz)', 'Kvarc');

INSERT INTO public.mineral_hierarchy(id, sub_type, super_type) VALUES
    (gen_random_uuid(), 'Serpentine', 'Silikati');

INSERT INTO public.mineral_hierarchy(id, sub_type, super_type) VALUES
    (gen_random_uuid(), 'Kvarc', 'Silikati');

