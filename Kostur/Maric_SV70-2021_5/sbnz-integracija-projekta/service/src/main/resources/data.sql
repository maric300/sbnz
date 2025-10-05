-- Brisanje postojećih podataka da bi se izbegli duplikati pri svakom pokretanju
DELETE FROM mineral_hierarchy;
DELETE FROM seasonal_tip;

DELETE FROM mineral;
--DELETE FROM users;
--DELETE FROM notification;

--INSERT INTO public.users(id, email, password, role) VALUES
    --(gen_random_uuid(), 'admin@mineral.com', '$2a$10$YcGSblI4aWfPhuXkaVPpDueqouKYSwnjXiv8ZAJJNRFliO1gxHChS', 'ADMIN');


-- Kvarc (generički, prva stavka iz tabele)
INSERT INTO public.mineral(id, accessibility, colors, difficulty, hardness_max, hardness_min, locations, luster, name, rock_types, streak_color, transparency) VALUES
    (gen_random_uuid(), 'BY_CAR', '["bezbojna", "žuta", "ljubičasta", "braon"]', 'EASY', 7.0, 7.0, '["Fruška Gora", "Cer", "Lece", "Novo Brdo", "Goleš"]', 'GLASSY', 'Kvarc', '["razne stene"]', 'bela', 'TRANSPARENT');

-- Kvarc (Gorski kristal)
INSERT INTO public.mineral(id, accessibility, colors, difficulty, hardness_max, hardness_min, locations, luster, name, rock_types, streak_color, transparency) VALUES
    (gen_random_uuid(), 'BY_CAR', '["bezbojna"]', 'EASY', 7.0, 7.0, '["Fruška Gora", "Cer", "Parlog", "Dobrača"]', 'GLASSY', 'Kvarc (Gorski kristal)', '["razne stene"]', 'bela', 'TRANSPARENT');

-- Kvarc (Ametist)
INSERT INTO public.mineral(id, accessibility, colors, difficulty, hardness_max, hardness_min, locations, luster, name, rock_types, streak_color, transparency) VALUES
    (gen_random_uuid(), 'ON_FOOT', '["ljubičasta"]', 'MEDIUM', 7.0, 7.0, '["Lece", "Kučevo", "Antina Čuka"]', 'GLASSY', 'Kvarc (Ametist)', '["andezit", "dacit"]', 'bela', 'TRANSPARENT');

-- Kvarc (Citrin)
INSERT INTO public.mineral(id, accessibility, colors, difficulty, hardness_max, hardness_min, locations, luster, name, rock_types, streak_color, transparency) VALUES
    (gen_random_uuid(), 'BY_CAR', '["žuta"]', 'HARD', 7.0, 7.0, '[]', 'GLASSY', 'Kvarc (Citrin)', '["razne stene"]', 'bela', 'TRANSPARENT');

-- Kvarc (Dimni kvarc)
INSERT INTO public.mineral(id, accessibility, colors, difficulty, hardness_max, hardness_min, locations, luster, name, rock_types, streak_color, transparency) VALUES
    (gen_random_uuid(), 'ON_FOOT', '["braon", "crna"]', 'MEDIUM', 7.0, 7.0, '["Cer", "Parlog"]', 'GLASSY', 'Kvarc (Dimni kvarc)', '["granit", "pegmatit"]', 'bela', 'TRANSLUCENT');

-- Kalcedon
INSERT INTO public.mineral(id, accessibility, colors, difficulty, hardness_max, hardness_min, locations, luster, name, rock_types, streak_color, transparency) VALUES
    (gen_random_uuid(), 'BY_CAR', '["bela", "siva", "plava", "braon", "roze", "crna"]', 'EASY', 7.0, 6.0, '["Fruška Gora", "Aranđelovac", "Kragujevac", "Niš", "Novo Brdo", "Gornji Milanovac"]', 'GREASY', 'Kalcedon', '["granit", "serpentinit", "sedimentne stene"]', 'bela', 'TRANSPARENT');

-- Kalcedon (Ahat)
INSERT INTO public.mineral(id, accessibility, colors, difficulty, hardness_max, hardness_min, locations, luster, name, rock_types, streak_color, transparency) VALUES
    (gen_random_uuid(), 'BY_CAR', '["bezbojna", "bela", "žuta", "crvena", "zelena", "braon", "crna"]', 'EASY', 7.0, 6.0, '["Fruška Gora (Hopovo)", "Rgotina", "Gornji Milanovac (Kremenjača)"]', 'GREASY', 'Kalcedon (Ahat)', '["vučji dacit", "serpentinit"]', 'bela', 'TRANSPARENT');

-- Kalcedon (Jaspis)
INSERT INTO public.mineral(id, accessibility, colors, difficulty, hardness_max, hardness_min, locations, luster, name, rock_types, streak_color, transparency) VALUES
    (gen_random_uuid(), 'BY_CAR', '["crvena", "smeđa", "žuta", "zelena", "šarena"]', 'EASY', 7.0, 6.5, '["Fruška Gora", "Lece", "Gornji Milanovac (Kremenjača)", "Niš"]', 'GLASSY', 'Kalcedon (Jaspis)', '["sedimentne stene", "vulkanske stene"]', 'bela do žuta', 'OPAQUE');

-- Kalcedon (Karneol)
INSERT INTO public.mineral(id, accessibility, colors, difficulty, hardness_max, hardness_min, locations, luster, name, rock_types, streak_color, transparency) VALUES
    (gen_random_uuid(), 'BY_CAR', '["narandžasta", "crvena"]', 'EASY', 7.0, 6.0, '["Fruška Gora (Ugljarevac)", "Kragujevac"]', 'GREASY', 'Kalcedon (Karneol)', '["sedimentne stene"]', 'bela', 'TRANSPARENT');

-- Opal
INSERT INTO public.mineral(id, accessibility, colors, difficulty, hardness_max, hardness_min, locations, luster, name, rock_types, streak_color, transparency) VALUES
    (gen_random_uuid(), 'ON_FOOT', '["bezbojna", "bela", "žuta", "crvena", "zelena", "braon", "crna"]', 'MEDIUM', 6.5, 5.5, '["Fruška Gora", "Ramada", "Klobukar", "Veluće", "Gaj", "Lažine", "Gladno Selo"]', 'GLASSY', 'Opal', '["serpentinit", "sedimentne stene", "harzburgit"]', 'bela', 'TRANSLUCENT');

-- Beril (Akvamarin)
INSERT INTO public.mineral(id, accessibility, colors, difficulty, hardness_max, hardness_min, locations, luster, name, rock_types, streak_color, transparency) VALUES
    (gen_random_uuid(), 'ON_FOOT', '["plava", "tirkizna"]', 'HARD', 8.0, 7.5, '["Cer", "Parlog", "Aranđelovac (Bukovic, Vagan)"]', 'GLASSY', 'Beril (Akvamarin)', '["pegmatit"]', 'bela', 'TRANSPARENT');

-- Granat
INSERT INTO public.mineral(id, accessibility, colors, difficulty, hardness_max, hardness_min, locations, luster, name, rock_types, streak_color, transparency) VALUES
    (gen_random_uuid(), 'ON_FOOT', '["crvena", "smeđa", "ljubičasta"]', 'MEDIUM', 7.5, 6.5, '["Jastrebac", "Prokuplje", "Srbija"]', 'GLASSY', 'Granat', '["gnajs", "mikasist"]', 'bela', 'TRANSLUCENT');

-- Turmalin
INSERT INTO public.mineral(id, accessibility, colors, difficulty, hardness_max, hardness_min, locations, luster, name, rock_types, streak_color, transparency) VALUES
    (gen_random_uuid(), 'ON_FOOT', '["crna"]', 'HARD', 7.5, 7.0, '["Cer", "Onić", "Rašče", "Bujanovac", "Lebane"]', 'GLASSY', 'Turmalin', '["pegmatit"]', 'bela', 'TRANSLUCENT');

-- Rodohrozit
INSERT INTO public.mineral(id, accessibility, colors, difficulty, hardness_max, hardness_min, locations, luster, name, rock_types, streak_color, transparency) VALUES
    (gen_random_uuid(), 'ON_FOOT', '["ružičasto-crven"]', 'HARD', 4.5, 3.5, '[]', 'PEARLY', 'Rodohrozit', '["metamorfne stene"]', 'bela', 'TRANSPARENT');

-- Fluorit
INSERT INTO public.mineral(id, accessibility, colors, difficulty, hardness_max, hardness_min, locations, luster, name, rock_types, streak_color, transparency) VALUES
    (gen_random_uuid(), 'BY_CAR', '["ljubičasta", "zelena", "plava", "žuta"]', 'MEDIUM', 4.0, 4.0, '["Ravnaija", "Pantelići"]', 'GLASSY', 'Fluorit', '["karbonatne stene"]', 'bela', 'TRANSPARENT');

-- Serpentinit (umesto Serpentine)
INSERT INTO public.mineral(id, accessibility, colors, difficulty, hardness_max, hardness_min, locations, luster, name, rock_types, streak_color, transparency) VALUES
    (gen_random_uuid(), 'BY_CAR', '["zelena, crna"]', 'EASY', 4.0, 2.5, '["Fruška Gora", "Kragujevac"]', 'GREASY', 'Serpentinit', '["ultramafit"]', 'bela', 'TRANSLUCENT');

-- Kijanit
INSERT INTO public.mineral(id, accessibility, colors, difficulty, hardness_max, hardness_min, locations, luster, name, rock_types, streak_color, transparency) VALUES
    (gen_random_uuid(), 'ON_FOOT', '["plava", "bela", "zelena", "siva"]', 'MEDIUM', 7.0, 4.5, '["Cer", "Vrn", "Prokuplje"]', 'PEARLY', 'Kijanit', '["gnajs", "škriljci"]', 'bela', 'TRANSLUCENT');

-- Hovit
INSERT INTO public.mineral(id, accessibility, colors, difficulty, hardness_max, hardness_min, locations, luster, name, rock_types, streak_color, transparency) VALUES
    (gen_random_uuid(), 'BY_CAR', '["bela"]', 'EASY', 3.5, 3.5, '["Jarandol"]', 'DULL', 'Hovit', '["marl", "škriljci"]', 'bela', 'OPAQUE');

-- Malahit
INSERT INTO public.mineral(id, accessibility, colors, difficulty, hardness_max, hardness_min, locations, luster, name, rock_types, streak_color, transparency) VALUES
    (gen_random_uuid(), 'BY_CAR', '["zelena"]', 'MEDIUM', 4.0, 3.5, '["Rudnik"]', 'GLASSY', 'Malahit', '["limestone"]', 'zelena', 'OPAQUE');

-- Magnezit
INSERT INTO public.mineral(id, accessibility, colors, difficulty, hardness_max, hardness_min, locations, luster, name, rock_types, streak_color, transparency) VALUES
    (gen_random_uuid(), 'ON_FOOT', '["bela", "siva"]', 'MEDIUM', 4.5, 3.5, '["Zlatibor"]', 'DULL', 'Magnezit', '["serpentinit"]', 'bela', 'OPAQUE');

-- Hromit
INSERT INTO public.mineral(id, accessibility, colors, difficulty, hardness_max, hardness_min, locations, luster, name, rock_types, streak_color, transparency) VALUES
    (gen_random_uuid(), 'ON_FOOT', '["crna"]', 'HARD', 5.5, 5.5, '["Zlatibor"]', 'METALLIC', 'Hromit', '["serpentinit"]', 'smeđa', 'OPAQUE');


-- ==================================================
--           HIJERARHIJA
-- ==================================================

INSERT INTO public.mineral_hierarchy(id, sub_type, super_type) VALUES
    (gen_random_uuid(), 'Kvarc (Ametist)', 'Kvarc');
INSERT INTO public.mineral_hierarchy(id, sub_type, super_type) VALUES
    (gen_random_uuid(), 'Kvarc (Gorski kristal)', 'Kvarc');
INSERT INTO public.mineral_hierarchy(id, sub_type, super_type) VALUES
    (gen_random_uuid(), 'Kvarc (Citrin)', 'Kvarc');
INSERT INTO public.mineral_hierarchy(id, sub_type, super_type) VALUES
    (gen_random_uuid(), 'Kvarc (Dimni kvarc)', 'Kvarc');
INSERT INTO public.mineral_hierarchy(id, sub_type, super_type) VALUES
    (gen_random_uuid(), 'Kalcedon', 'Kvarc');
INSERT INTO public.mineral_hierarchy(id, sub_type, super_type) VALUES
    (gen_random_uuid(), 'Kalcedon (Ahat)', 'Kalcedon');
INSERT INTO public.mineral_hierarchy(id, sub_type, super_type) VALUES
    (gen_random_uuid(), 'Kalcedon (Jaspis)', 'Kalcedon');
INSERT INTO public.mineral_hierarchy(id, sub_type, super_type) VALUES
    (gen_random_uuid(), 'Kalcedon (Karneol)', 'Kalcedon');
INSERT INTO public.mineral_hierarchy(id, sub_type, super_type) VALUES
    (gen_random_uuid(), 'Serpentinit', 'Silikati');
INSERT INTO public.mineral_hierarchy(id, sub_type, super_type) VALUES
    (gen_random_uuid(), 'Kvarc', 'Silikati');