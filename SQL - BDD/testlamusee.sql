-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : mer. 28 juin 2023 à 11:23
-- Version du serveur : 8.0.31
-- Version de PHP : 8.0.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `testlamusee`
--

-- --------------------------------------------------------

--
-- Structure de la table `doctrine_migration_versions`
--

DROP TABLE IF EXISTS `doctrine_migration_versions`;
CREATE TABLE IF NOT EXISTS `doctrine_migration_versions` (
  `version` varchar(191) COLLATE utf8mb3_unicode_ci NOT NULL,
  `executed_at` datetime DEFAULT NULL,
  `execution_time` int DEFAULT NULL,
  PRIMARY KEY (`version`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `download_modele`
--

DROP TABLE IF EXISTS `download_modele`;
CREATE TABLE IF NOT EXISTS `download_modele` (
  `id` int NOT NULL AUTO_INCREMENT,
  `url` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `texte` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `download_modele`
--

INSERT INTO `download_modele` (`id`, `url`, `texte`) VALUES
(1, 'test', 'test'),
(6, 'URL TEST', 'L\'Amusée'),
(7, 'https://i.postimg.cc/yNhTQK2b/', 'L\'Amusée m\'a permis de trouver');

-- --------------------------------------------------------

--
-- Structure de la table `images_modele`
--

DROP TABLE IF EXISTS `images_modele`;
CREATE TABLE IF NOT EXISTS `images_modele` (
  `id` int NOT NULL AUTO_INCREMENT,
  `texte` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `url` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `images_modele`
--

INSERT INTO `images_modele` (`id`, `texte`, `url`) VALUES
(1, 'J\'ai fait confectionner ma robe de mariée chez L\'Amusée, et j\'ai adoré le concept de pouvoir mixer les modèles', 'https://i.postimg.cc/52CgzLg1/photo-cliente-3.png'),
(2, 'Un grand merci pour la qualité de votre travail', 'https://i.postimg.cc/pT2J37HX/photo-cliente-2-1.png'),
(3, 'Belle expérience avec L\'Amusée. Une équipe très pro, réactive et à l\'écoute', 'https://i.postimg.cc/KvVQ4JnJ/photo-l-va-2-1.png'),
(4, 'Les robes sont originales et de très bonne qualité', 'https://i.postimg.cc/ZqzcxnsN/Photos-cliente-1-1.png'),
(5, 'L\'Amusée m\'a permis de trouver la robe parfaite pour mon mariage civil', 'https://i.postimg.cc/yNhTQK2b/Photo-3-1.png');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
