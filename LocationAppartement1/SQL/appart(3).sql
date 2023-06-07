-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : mer. 07 juin 2023 à 08:42
-- Version du serveur : 8.0.27
-- Version de PHP : 8.0.13

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `appartements`
--

-- --------------------------------------------------------

--
-- Structure de la table `admin`
--

DROP TABLE IF EXISTS `admin`;
CREATE TABLE IF NOT EXISTS `admin` (
  `id` int NOT NULL AUTO_INCREMENT,
  `login` varchar(30) NOT NULL,
  `mdp` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `admin`
--

INSERT INTO `admin` (`id`, `login`, `mdp`) VALUES
(2, 'toma', '123');

-- --------------------------------------------------------

--
-- Structure de la table `appartements`
--

DROP TABLE IF EXISTS `appartements`;
CREATE TABLE IF NOT EXISTS `appartements` (
  `numappart` int NOT NULL AUTO_INCREMENT,
  `rue` int DEFAULT NULL,
  `ville` varchar(50) DEFAULT NULL,
  `CP` int DEFAULT NULL,
  `etage` int DEFAULT NULL,
  `prix_log` int DEFAULT NULL,
  `prix_charg` int DEFAULT NULL,
  `ascenseur` int NOT NULL,
  `preavis` int NOT NULL,
  `date_libre` date DEFAULT NULL,
  `id_typeAppart` int NOT NULL,
  `arrondissement_num` int NOT NULL,
  `taille` int NOT NULL,
  `departement` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`numappart`),
  KEY `id_typeAppart` (`id_typeAppart`),
  KEY `arrondissement_dem` (`arrondissement_num`)
) ENGINE=InnoDB AUTO_INCREMENT=111 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `appartements`
--

INSERT INTO `appartements` (`numappart`, `rue`, `ville`, `CP`, `etage`, `prix_log`, `prix_charg`, `ascenseur`, `preavis`, `date_libre`, `id_typeAppart`, `arrondissement_num`, `taille`, `departement`) VALUES
(1, 123, 'Paris', 75000, 4, 500, 550, 1, 1, '2022-01-01', 1, 1, 30, 'departementA'),
(2, 456, 'Lyon', 69000, 2, 600, 650, 0, 0, '2022-02-01', 2, 2, 40, 'departementB'),
(3, 789, 'Marseille', 13000, 1, 700, 750, 1, 0, '2022-03-01', 3, 3, 75, 'departementC'),
(4, 246, 'Lille', 59000, 5, 400, 450, 0, 1, '2022-04-01', 4, 4, 80, 'departementD'),
(5, 369, 'Toulouse', 31000, 3, 800, 850, 1, 0, '2022-05-01', 5, 5, 60, ''),
(6, 159, 'Bordeaux', 33000, 4, 450, 500, 0, 1, '2022-06-01', 1, 6, 25, ''),
(7, 753, 'Strasbourg', 67000, 2, 550, 600, 1, 0, '2022-07-01', 2, 7, 42, ''),
(8, 951, 'Nantes', 44000, 1, 650, 700, 0, 1, '2022-08-01', 3, 8, 50, ''),
(9, 852, 'Nice', 6000, 5, 1400, 600, 1, 0, '2022-09-01', 4, 9, 80, ''),
(10, 741, 'Rennes', 35000, 3, 1400, 650, 0, 1, '2022-10-01', 5, 10, 100, ''),
(12, 2, 'Paris', 75008, 2, 2200, 300, 1, 2, '2023-05-01', 2, 8, 75, ''),
(25, 4, 'Paris', 75001, 5, 1500, 200, 1, 1, '2023-04-15', 1, 1, 50, ''),
(42, 9, 'Paris', 75011, 3, 1200, 100, 0, 1, '2023-04-10', 3, 10, 35, ''),
(75, 7, 'Paris', 75020, 1, 1300, 100, 1, 1, '2023-05-01', 2, 4, 45, ''),
(102, 1, 'Paris', 75020, 6, 900, 50, 0, 2, '2023-04-01', 1, 6, 30, ''),
(107, 6, 'Toulouse', 12345, 1, 700, 100, 1, 2, '2023-03-29', 3, 4, 50, ''),
(108, 9, 'Bordeaux', 33000, 2, 800, 100, 1, 2, '2022-06-01', 2, 9, 60, '');

-- --------------------------------------------------------

--
-- Structure de la table `arrondissement`
--

DROP TABLE IF EXISTS `arrondissement`;
CREATE TABLE IF NOT EXISTS `arrondissement` (
  `arrondissement_num` int NOT NULL AUTO_INCREMENT,
  `libelle` varchar(100) NOT NULL,
  PRIMARY KEY (`arrondissement_num`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `arrondissement`
--

INSERT INTO `arrondissement` (`arrondissement_num`, `libelle`) VALUES
(1, '1er Arrondissement'),
(2, '2ème Arrondissement'),
(3, '3ème Arrondissement'),
(4, '4ème Arrondissement'),
(5, '5ème Arrondissement'),
(6, '6ème Arrondissement'),
(7, '7ème Arrondissement'),
(8, '8ème Arrondissement'),
(9, '9ème Arrondissement'),
(10, '10ème Arrondissement');

-- --------------------------------------------------------

--
-- Structure de la table `banque`
--

DROP TABLE IF EXISTS `banque`;
CREATE TABLE IF NOT EXISTS `banque` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nom` varchar(50) DEFAULT NULL,
  `adresse` varchar(50) DEFAULT NULL,
  `ville` varchar(50) DEFAULT NULL,
  `CP` varchar(50) DEFAULT NULL,
  `tel` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `banque`
--

INSERT INTO `banque` (`id`, `nom`, `adresse`, `ville`, `CP`, `tel`) VALUES
(1, 'Banque de France', '1, place de la bourse', 'Paris', '75002', 123456789),
(2, 'Crédit Agricole', '2, rue des alpes', 'Lyon', '69001', 123456788),
(3, 'BNP Paribas', '3, boulevard des docks', 'Marseille', '13005', 123456787),
(4, 'Société Générale', '4, avenue du nord', 'Lille', '59000', 123456786),
(5, 'LCL', '5, rue du midi', 'Toulouse', '31000', 123456785),
(6, 'La Banque Postale', '6, avenue du sud', 'Bordeaux', '33000', 123456784),
(7, 'HSBC', '10, rue de la mer', 'Rennes', '35000', 123456780);

-- --------------------------------------------------------

--
-- Structure de la table `clients`
--

DROP TABLE IF EXISTS `clients`;
CREATE TABLE IF NOT EXISTS `clients` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nom` varchar(50) DEFAULT NULL,
  `Prenom` varchar(50) DEFAULT NULL,
  `adresse` varchar(50) DEFAULT NULL,
  `Ville` varchar(50) DEFAULT NULL,
  `CP` int DEFAULT NULL,
  `tel` int DEFAULT NULL,
  `DateNaiss` varchar(30) NOT NULL,
  `RIB` int NOT NULL,
  `mdp` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `clients`
--

INSERT INTO `clients` (`id`, `nom`, `Prenom`, `adresse`, `Ville`, `CP`, `tel`, `DateNaiss`, `RIB`, `mdp`) VALUES
(1, 'Durant', 'Jeanne', '1, rue de la paix', 'Paris', 75000, 123456789, '2000-02-1', 748219506, '123'),
(4, 'Petit', 'Anne', '4, rue du marché', 'Lyon', 69000, 123456786, '1997-05-04', 849703216, '123'),
(5, 'Lefebvre', 'Paul', '5, avenue du soleil', 'Lille', 31000, 123456785, '1996-06-05', 370196854, '123'),
(7, 'Garcia', 'David', '7, avenue des fleurs', 'Strasbourg', 67000, 123456783, '1992-08-07', 506918247, '123'),
(8, 'Robert', 'Emilie', '8, boulevard de la plage', 'Nantes', 44000, 123456782, '1993-05-07', 182670435, '123'),
(9, 'Smith', 'William', '9, rue de la montagne', 'Nice', 6000, 123456781, '1996-6-14', 395017826, '123'),
(10, 'Johnson', 'John', '10, avenue des arbres', 'Rennes', 35000, 123456780, '1991-10-11', 763184295, '123');

-- --------------------------------------------------------

--
-- Structure de la table `demandes`
--

DROP TABLE IF EXISTS `demandes`;
CREATE TABLE IF NOT EXISTS `demandes` (
  `num_dem` int NOT NULL AUTO_INCREMENT,
  `num_cli` int DEFAULT NULL,
  `date_limite` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `prix_min` int DEFAULT NULL,
  `prix_max` int DEFAULT NULL,
  `taille` int NOT NULL,
  `ville` varchar(10) NOT NULL,
  `idType` int DEFAULT NULL,
  `idArrondi` int NOT NULL,
  `departement` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`num_dem`),
  KEY `num_cli` (`num_cli`),
  KEY `idType` (`idType`),
  KEY `idArrondi` (`idArrondi`)
) ENGINE=InnoDB AUTO_INCREMENT=88 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `demandes`
--

INSERT INTO `demandes` (`num_dem`, `num_cli`, `date_limite`, `prix_min`, `prix_max`, `taille`, `ville`, `idType`, `idArrondi`, `departement`) VALUES
(3, 7, '2022-07-01', 550, 600, 30, 'Nice', 3, 8, ''),
(4, 5, '2022-08-01', 800, 1200, 100, 'Paris', 4, 7, ''),
(5, 9, '2022-09-01', 500, 900, 80, 'Lyon', 1, 6, ''),
(8, 10, '2022-12-01', 650, 1000, 80, 'Marseille', 5, 4, ''),
(9, 1, '2023-01-01', 500, 700, 70, 'Toulouse', 1, 3, ''),
(10, 8, '2023-02-01', 600, 950, 75, 'Lille', 5, 2, ''),
(85, 4, '29/03/2023', 500, 1000, 30, 'Paris', 1, 1, '');

-- --------------------------------------------------------

--
-- Structure de la table `locataires`
--

DROP TABLE IF EXISTS `locataires`;
CREATE TABLE IF NOT EXISTS `locataires` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nom` varchar(50) DEFAULT NULL,
  `prenom` varchar(50) DEFAULT NULL,
  `dateNaissance` varchar(50) DEFAULT NULL,
  `tel` int DEFAULT NULL,
  `RIB` int DEFAULT NULL,
  `idBanque` int DEFAULT NULL,
  `mdp` varchar(30) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idBanque` (`idBanque`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `locataires`
--

INSERT INTO `locataires` (`id`, `nom`, `prenom`, `dateNaissance`, `tel`, `RIB`, `idBanque`, `mdp`) VALUES
(1, 'Dupont', 'Jean', '2000-01-01', 123456789, 123456789, 1, '123'),
(2, 'Martin', 'Paul', '1999-02-02', 123456788, 123467890, 2, ''),
(3, 'Dubois', 'Jean-paul', '1998-03-03', 123456787, 124567802, 3, ''),
(4, 'Roche', 'Jacques', '1997-04-04', 123456786, 234568903, 4, ''),
(5, 'Leroy', 'Claire', '1996-05-05', 123456785, 134568904, 5, ''),
(6, 'Moreau', 'David', '1995-06-06', 123456784, 124578905, 6, ''),
(7, 'Girard', 'Sophie', '1994-07-07', 123456783, 125678906, 7, ''),
(8, 'Simon', 'Laure', '1993-08-08', 123456782, 124678907, 4, ''),
(9, 'Lambert', 'Julie', '1992-09-09', 123456781, 345678918, 6, ''),
(10, 'Faure', 'Eric', '1991-10-10', 123456780, 145678909, 2, ''),
(18, 'Roux', 'Claire', '1997-07-06', 123456784, 924503617, NULL, ''),
(19, 'Durand', 'Marie', '1999-03-02', 123456788, 631508942, NULL, '123');

-- --------------------------------------------------------

--
-- Structure de la table `occuper`
--

DROP TABLE IF EXISTS `occuper`;
CREATE TABLE IF NOT EXISTS `occuper` (
  `numappart` int NOT NULL,
  `id_locataire` int NOT NULL,
  `date_debut` date DEFAULT NULL,
  `date_fin` date DEFAULT NULL,
  PRIMARY KEY (`numappart`,`id_locataire`),
  KEY `id` (`id_locataire`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `occuper`
--

INSERT INTO `occuper` (`numappart`, `id_locataire`, `date_debut`, `date_fin`) VALUES
(1, 1, '2022-01-15', '2022-01-31'),
(2, 2, '2022-02-10', '2022-02-28'),
(3, 3, '2022-03-01', '2022-03-15'),
(4, 4, '2022-04-01', '2022-04-20'),
(5, 5, '2022-05-10', '2022-05-31'),
(6, 1, '2022-06-01', '2022-06-15'),
(7, 2, '2022-07-01', '2022-07-31'),
(8, 3, '2022-08-01', '2022-08-15'),
(9, 4, '2022-09-01', '2022-09-30'),
(10, 5, '2022-10-01', '2022-10-31');

-- --------------------------------------------------------

--
-- Structure de la table `posseder`
--

DROP TABLE IF EXISTS `posseder`;
CREATE TABLE IF NOT EXISTS `posseder` (
  `numappart` int NOT NULL,
  `id_proprietaire` int NOT NULL,
  PRIMARY KEY (`numappart`,`id_proprietaire`),
  KEY `id` (`id_proprietaire`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `posseder`
--

INSERT INTO `posseder` (`numappart`, `id_proprietaire`) VALUES
(10, 1),
(5, 2),
(7, 3),
(9, 4),
(2, 5),
(8, 6),
(3, 7),
(6, 8),
(4, 9),
(1, 10);

-- --------------------------------------------------------

--
-- Structure de la table `proprietaires`
--

DROP TABLE IF EXISTS `proprietaires`;
CREATE TABLE IF NOT EXISTS `proprietaires` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nom` varchar(50) DEFAULT NULL,
  `Prenom` varchar(50) DEFAULT NULL,
  `adresse` varchar(50) DEFAULT NULL,
  `Ville` varchar(50) DEFAULT NULL,
  `CP` int DEFAULT NULL,
  `tel` int DEFAULT NULL,
  `mdp` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `proprietaires`
--

INSERT INTO `proprietaires` (`id`, `nom`, `Prenom`, `adresse`, `Ville`, `CP`, `tel`, `mdp`) VALUES
(1, 'Dupont', 'Jacques', '1 rue de la Paix', 'Paris', 75000, 600000165, '123'),
(2, 'Martin', 'Garix', '2 avenue des Champs Elysées', 'Paris', 75016, 696800000, '123'),
(3, 'Leroy', 'Luc', '3 boulevard de la Villette', 'Lyon', 69003, 600001650, '123'),
(4, 'Rousseau', 'Emilie', '4 rue de la Soif', 'Lyon', 69006, 600001340, '123'),
(5, 'Dubois', 'David', '5 avenue des Ternes', 'Paris', 75017, 600016800, ''),
(6, 'Moreau', 'Laure', '6 rue de Belleville', 'Paris', 75019, 600067400, ''),
(7, 'Garcia', 'Pascal', '7 rue de la Roquette', 'Paris', 75011, 606450000, ''),
(8, 'Petit', 'Catherine', '8 avenue des Gobelins', 'Paris', 75013, 601650000, ''),
(9, 'Fournier', 'Véronique', '9 rue de Belleville', 'Paris', 75019, 648200000, ''),
(10, 'Roux', 'Anne', '10 avenue des Gobelins', 'Paris', 75013, 600006940, ''),
(11, 'Girard', 'Patrick', '11 rue de la Roquette', 'Paris', 75011, 603450000, ''),
(12, 'Lefebvre', 'Sylvie', '12 rue de Belleville', 'Paris', 75019, 606480000, '');

-- --------------------------------------------------------

--
-- Structure de la table `type_appart`
--

DROP TABLE IF EXISTS `type_appart`;
CREATE TABLE IF NOT EXISTS `type_appart` (
  `id_typeAppart` int NOT NULL AUTO_INCREMENT,
  `type` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id_typeAppart`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `type_appart`
--

INSERT INTO `type_appart` (`id_typeAppart`, `type`) VALUES
(1, 'Studio'),
(2, 'Appartement 1 chambre'),
(3, 'Appartement 2 chambres'),
(4, 'Appartement 3 chambres'),
(5, 'Maison');

-- --------------------------------------------------------

--
-- Structure de la table `visiter`
--

DROP TABLE IF EXISTS `visiter`;
CREATE TABLE IF NOT EXISTS `visiter` (
  `numappart` int NOT NULL,
  `idClient` int NOT NULL,
  `date_visite` varchar(11) DEFAULT NULL,
  PRIMARY KEY (`numappart`,`idClient`),
  KEY `id` (`idClient`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `visiter`
--

INSERT INTO `visiter` (`numappart`, `idClient`, `date_visite`) VALUES
(1, 10, '2022-11-01'),
(2, 9, '2022-12-01'),
(3, 8, '2023-01-01'),
(4, 7, '2023-02-01'),
(6, 5, '2023-04-01'),
(7, 4, '2023-05-01'),
(10, 1, '2023-08-01'),
(102, 10, '2023-03/30');

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `appartements`
--
ALTER TABLE `appartements`
  ADD CONSTRAINT `fk_arrondissementnum` FOREIGN KEY (`arrondissement_num`) REFERENCES `arrondissement` (`arrondissement_num`),
  ADD CONSTRAINT `fk_idtypeAppart` FOREIGN KEY (`id_typeAppart`) REFERENCES `type_appart` (`id_typeAppart`);

--
-- Contraintes pour la table `demandes`
--
ALTER TABLE `demandes`
  ADD CONSTRAINT `demandes_ibfk_1` FOREIGN KEY (`num_cli`) REFERENCES `clients` (`id`),
  ADD CONSTRAINT `idArrondi` FOREIGN KEY (`idArrondi`) REFERENCES `arrondissement` (`arrondissement_num`),
  ADD CONSTRAINT `idType` FOREIGN KEY (`idType`) REFERENCES `type_appart` (`id_typeAppart`),
  ADD CONSTRAINT `num_cli` FOREIGN KEY (`num_cli`) REFERENCES `clients` (`id`);

--
-- Contraintes pour la table `locataires`
--
ALTER TABLE `locataires`
  ADD CONSTRAINT `locataires_ibfk_1` FOREIGN KEY (`idBanque`) REFERENCES `banque` (`id`);

--
-- Contraintes pour la table `occuper`
--
ALTER TABLE `occuper`
  ADD CONSTRAINT `occuper_ibfk_1` FOREIGN KEY (`numappart`) REFERENCES `appartements` (`numappart`),
  ADD CONSTRAINT `occuper_ibfk_2` FOREIGN KEY (`id_locataire`) REFERENCES `locataires` (`id`);

--
-- Contraintes pour la table `posseder`
--
ALTER TABLE `posseder`
  ADD CONSTRAINT `posseder_ibfk_1` FOREIGN KEY (`numappart`) REFERENCES `appartements` (`numappart`),
  ADD CONSTRAINT `posseder_ibfk_2` FOREIGN KEY (`id_proprietaire`) REFERENCES `proprietaires` (`id`);

--
-- Contraintes pour la table `visiter`
--
ALTER TABLE `visiter`
  ADD CONSTRAINT `visiter_ibfk_1` FOREIGN KEY (`numappart`) REFERENCES `appartements` (`numappart`),
  ADD CONSTRAINT `visiter_ibfk_2` FOREIGN KEY (`idClient`) REFERENCES `clients` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
