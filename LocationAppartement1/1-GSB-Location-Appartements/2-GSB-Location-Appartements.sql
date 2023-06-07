-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Client :  127.0.0.1
-- Généré le :  Lun 01 Février 2016 à 12:17
-- Version du serveur :  5.6.17
-- Version de PHP :  5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données :  `GSB`
--

-- --------------------------------------------------------

--
-- Structure de la table `appartements`
--

CREATE TABLE IF NOT EXISTS `appartements` (
  `NUMAPPART` int(11) NOT NULL,
  `TYPAPPART` varchar(128) NOT NULL,
  `PRIX_LOC` decimal(10,2) DEFAULT NULL,
  `PRIX_CHARG` decimal(10,2) DEFAULT NULL,
  `RUE` varchar(255) NOT NULL,
  `ARRONDISSEMENT` int(11) NOT NULL,
  `ETAGE` int(11) DEFAULT NULL,
  `ASCENSEUR` tinyint(1) DEFAULT '1',
  `PREAVIS` tinyint(1) DEFAULT '0',
  `DATE_LIBRE` date NOT NULL,
  `NUMEROPROP` int(11) NOT NULL,
  `NUMEROLOC` int(11) NOT NULL,
  PRIMARY KEY (`NUMAPPART`),
  KEY `I_FK_APPARTEMENTS_PROPRIETAIRES` (`NUMEROPROP`),
  KEY `I_FK_APPARTEMENTS_LOCATAIRES` (`NUMEROLOC`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `appartements`
--

INSERT INTO `appartements` (`NUMAPPART`, `TYPAPPART`, `PRIX_LOC`, `PRIX_CHARG`, `RUE`, `ARRONDISSEMENT`, `ETAGE`, `ASCENSEUR`, `PREAVIS`, `DATE_LIBRE`, `NUMEROPROP`, `NUMEROLOC`) VALUES
(1, 'F1', '690.00', '90.00', '23 Parc de L''Industrie', 1, 1, 0, 0, '2008-02-01', 1, 512),
(2, 'F4', '1400.00', '270.00', '14 PL J FERRY', 12, 3, 0, 0, '2007-05-01', 12, 510),
(3, 'F2', '800.00', '150.00', '23 Av St Genest', 14, 10, 1, 0, '2007-07-01', 4, 501),
(4, 'F2', '820.00', '140.00', '104 AV MICHELIN', 2, 6, 0, 0, '2008-09-30', 6, 504),
(5, 'STUDIO', '510.00', '99.00', '25 rue de la Serpe', 16, 4, 1, 0, '2008-06-30', 2, 511),
(6, 'F1', '550.00', '120.00', '7 bis Rue du Centre', 13, 4, 1, 0, '2008-07-31', 1, 522),
(7, 'F5', '1500.00', '360.00', '8b Les Ronces', 6, 3, 0, 0, '2009-10-01', 13, 518),
(8, 'F1', '570.00', '110.00', '23 avI De la Goutte', 12, 9, 1, 0, '2008-08-31', 2, 521),
(9, 'F4', '1200.00', '310.00', '5 rue des Filatures', 5, 9, 1, 0, '2009-12-01', 11, 524),
(10, 'F3', '990.00', '130.00', '56 Champs du Clos', 7, 11, 1, 0, '2010-07-01', 8, 525),
(11, 'STUDIO', '490.00', '45.00', '10 Bv Berthelot', 3, 14, 1, 0, '2008-08-31', 6, 515),
(12, 'F5', '1500.00', '240.00', 'Av De La Victoire', 5, 5, 1, 0, '2008-05-31', 13, 514),
(13, 'F3', '1050.00', '135.00', '5 Place de la Mairie', 11, 8, 1, 1, '2007-03-01', 7, 500),
(14, 'STUDIO', '500.00', '75.00', '187 BIS BV J.moulin', 12, 4, 0, 0, '2007-06-01', 1, 520),
(15, 'STUDIO', '500.00', '65.00', '2 rue du 8 Juin 44', 5, 2, 0, 0, '2009-11-01', 5, 509),
(16, 'F3', '1250.00', '200.00', '7 les Ursulines', 10, 1, 0, 0, '2008-10-31', 8, 506),
(17, 'F2', '1000.00', '150.00', '1 rue la Folline', 16, 1, 0, 0, '2008-10-31', 5, 505),
(18, 'F1', '600.00', '80.00', '5 B Le Point du jour', 13, 2, 0, 0, '2008-06-30', 1, 523),
(19, 'F4', '1490.00', '310.00', '366 Rue de la Gare', 12, 10, 1, 0, '2010-01-01', 9, 508),
(20, 'F2', '980.00', '145.00', '98 Cours E Zola', 18, 7, 0, 0, '2008-08-31', 7, 507),
(21, 'F1', '510.00', '90.00', '87 Place Charingot', 7, 11, 1, 0, '2009-07-01', 2, 517),
(22, 'F2', '800.00', '120.00', '610 Cours Albert 1°', 11, 4, 1, 1, '2007-04-01', 3, 513),
(23, 'F4', '1300.00', '160.00', '12 Bv du Breuil', 10, 5, 0, 0, '2008-06-30', 10, 502),
(24, 'F5', '1600.00', '220.00', '6 ter place J Jaurès', 5, 6, 1, 0, '2008-07-31', 12, 516),
(25, 'STUDIO', '600.00', '90.00', '69 rue de Jurnieux', 20, 6, 0, 0, '2016-01-21', 4, 500);

-- --------------------------------------------------------

--
-- Structure de la table `arrondissement`
--

CREATE TABLE IF NOT EXISTS `arrondissement` (
  `ARRONDISS_DEM` int(11) NOT NULL,
  PRIMARY KEY (`ARRONDISS_DEM`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `arrondissement`
--

INSERT INTO `arrondissement` (`ARRONDISS_DEM`) VALUES
(1),
(2),
(3),
(4),
(5),
(6),
(7),
(8),
(9),
(10),
(11),
(12),
(13),
(14),
(15),
(16),
(17),
(18),
(19),
(20),
(21),
(22),
(23),
(24),
(25),
(26),
(27),
(28),
(29),
(30),
(31),
(32),
(33),
(34),
(35),
(36),
(37),
(38),
(39),
(40),
(41),
(42),
(43);

-- --------------------------------------------------------

--
-- Structure de la table `clients`
--

CREATE TABLE IF NOT EXISTS `clients` (
  `NUM_CLI` int(11) NOT NULL,
  `NOM_CLI` varchar(128) NOT NULL,
  `PRENOM_CLI` varchar(128) NOT NULL,
  `ADRESSE_CLI` varchar(255) NOT NULL,
  `CODEVILLE_CLI` varchar(128) NOT NULL,
  `TEL_CLI` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`NUM_CLI`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `clients`
--

INSERT INTO `clients` (`NUM_CLI`, `NOM_CLI`, `PRENOM_CLI`, `ADRESSE_CLI`, `CODEVILLE_CLI`, `TEL_CLI`) VALUES
(100, 'RIERON', 'Colette', '20, rue du Pressoir', '29600 Puisant', '46 81 40 84'),
(101, 'ROISSENON', 'Danièle', '60 Allée de Provence', '28210 Sogent', '30 95 31 29'),
(102, 'MONFFER', 'Gérard', '99 Avenue de la Gare', '28130 St Piliat', '60 06 51 09'),
(103, 'MAMER', 'Henri', '84 Chemin de Fréville', '90520 LEGLY', NULL),
(104, 'MIACHE', 'Elise', '45 Bv Auch', '90520 LENGLE', '48 57 64 38'),
(105, 'PAHE', 'Bernard', '29 rue de Pinpin', '41520 EGLONS', '39 61 11 01'),
(106, 'LAMINETE', 'Claude', '217, rue de la République', '76000 ROUEN', '43 04 73 69'),
(107, 'BOUDINOT', 'Martine', '24, rue Mont Marbel', '72100 Le Mans', '64 37 27 70'),
(108, 'ILDEBERT', 'Claude', '41 Allée des Rosese', '72000 Le Mans', '46 95 10 42'),
(109, 'TIELON', 'Patrick', '3 rue Tinrionnet', '18400 Douziers', '42 45 66 33'),
(110, 'BIELLET', 'Brigitte', '23 rue Pinson', '08400 Vouziers', '30 92 59 52'),
(112, 'PAMLEAC', 'Marie Loui', '18 Allée Bleue', '48100 Marvejols', '40 84 07 76'),
(113, 'TIVOLIES', 'Claude', '40 Rue du Petit Chose', '48230 ISPAGNAC', '60 01 92 66'),
(114, 'BIROUVES', 'Aliette', 'Route de Viachon', '44220 ISPAGNAC', '39 78 80 41'),
(115, 'CIVIER', 'Edouard', 'Le Coussinel', '48300 Montrondant', '48 29 45 91'),
(116, 'LIVIE', 'Marie', '58 Rue des Tilleuls', '48100 Montrodat', '34 50 81 33'),
(117, 'FRAYSSIER', 'Thérèse Ma', '310  Rue Royale', '45000 Orléans', '39 58 55 65'),
(118, 'ISORE', 'Anne', '344 rue du Baudet', '92400 Courbevoie', '43 62 07 79'),
(119, 'CHAMPAGNE', 'François', '207 allée du grand Bois', '03500 Lyzendre', '34 69 01 40'),
(121, 'TOUPRET', 'Danièle', '18 rue du Bouchut', '04110 Vendat', NULL),
(122, 'GIRAUDON', 'Philippe', '53 rue J. Jaurès', '13004 Marseille', '03 48 46 76'),
(124, 'PANTIN', 'Jean Franç', '19 Allée des Sables', '13000 Marseille', '48 54 36 18'),
(127, 'MARTIN', 'Catherine', '7 rue Lantier', '13004 Marseille', '48 33 05 24'),
(128, 'TOTREL', 'Patricia', '43 Ch des Jonquilles', '13013 Marseille', NULL),
(129, 'BRUNETI', 'Marcelle', 'Les Plaines', '83000 Toulon', '42 45 03 80'),
(131, 'GERTON', 'Catherine', '86 Bv  Baptiste Abelin', '83000 Toulon', '48 46 71 87'),
(132, 'TAPIME', 'Claude', '213 Lot Les Acacias', '82500 Laneyne', '39 15 82 80'),
(133, 'LUDION', 'Nelly', '78 rue du Vélodrome', '82100 Coulon', '42 87 08 10'),
(134, 'TERMAT', 'Colette', '67 rue M de Vaire', '84000 Moulen', '47 00 28 49'),
(136, 'ELAGUIRA', 'Brigitte', '3 Rue des Reines', '83500 La Seyne /Mer', '48 38 02 98'),
(138, 'TISSIELLE', 'Pierre', '43 Allée du Port', '34790 Emabeles', '39 62 34 40'),
(139, 'TOUTON', 'Roger', '54 rue de Chantelle', '34070 Montpellier', NULL),
(140, 'TOUTON', 'Danielle', '54 Rue Chantelle', '34070 Montpellier', NULL),
(141, 'LEFEUVRE', 'Didier', 'Le Jay', '19270 Donzenac', '43 02 33 83'),
(144, 'PSYCHO', 'Antoine', '4 rue du Pont Neuf', '30000 Nimes', '48 68 01 69'),
(145, 'MUTALACO', 'Laurence', '28 rue de Tintin', '30000 Nimes', '40 38 16 59'),
(146, 'SURANO', 'Raymond', '4 Bv Foch', '30100 Alès', '48 70 92 28'),
(147, 'BEAUGRANDIN', 'Alphonse', '4 Quai de Saône', '69004 Lyon', '42 01 02 19'),
(148, 'DEPINANT', 'Eliane', '4 Bv Fauriat', '42000 St Etienne', NULL),
(149, 'DAMARIO', 'Jacques', '8 rue des Arènes', '30000 Nimes', '47 21 87 87'),
(150, 'ARPION', 'Françoise', '6 Impasse du Mur', '30000 Nimes', '46 77 34 96'),
(151, 'PRIFOLOS', 'Achille', '5 Bv des Batignolles', '75017 Paris', '42 09 32 15'),
(153, 'FIPANARD', 'Mauricette', '58 R Antoine Durafour', '42000 St Etienne', '39 86 02 18'),
(154, 'GRANGETTE', 'Julie', '20 Impasse des Lilas', '69000 Lyon', '48 57 24 62'),
(155, 'PABLO', 'Géraldine', '41 Av J Claussat', '63000 Clermont Ferra', '60 75 44 24'),
(156, 'CHAPOSSIER', 'Edouard', '25 Av A Einstein', '45000 Orléans', '48 54 83 73'),
(157, 'ROVEPRA', 'Yvette', 'chemin de Merley', '82390 Pillars', '40 30 37 99'),
(158, 'REMOUL', 'Paulette', '3 rue M Ravel', '62270 St Piroest', '34 97 56 96'),
(160, 'DAQUIVA', 'Françoise', '25 Bv de la mer', '83340 Le Cannet', '40 96 95 81'),
(161, 'LABRIANE', 'Sophie', '49 cours Fauriel', '42000 St Etienne', '48 45 08 92'),
(162, 'TONUCCI', 'Bernard', '164 rue Chat. Gaillard', '69100 Villeurbanne', '44 63 08 33'),
(163, 'ANTON', 'Jean', '47 Rue des Louves', '38000 Grenoble', '48 87 14 96'),
(165, 'CARNATE', 'Aliette', '75 rte de Charonne', '42000 St Chamond', NULL),
(166, 'LARBRET', 'Claude', '8 cours Fauriel', '42100 St Etienne', '42 08 15 35'),
(167, 'LISSIN', 'Marie France', '37, rue de la Côte', '32790 Prabels', '49 01 06 20'),
(168, 'LASTAT', 'Maryse', '32 Av G Pompidou', '03100 Moulins', '43 43 99 27'),
(169, 'BEDAINE', 'René', '37 rue Lazare Carnot', '83340 Monte Luc', '46 87 60 29'),
(170, 'MAMBOT', 'Gilles', '66 Av de Sarlat', '46600 Mouillac', '48 90 70 05'),
(171, 'Cornu', 'Nicolas', '16 rue de la boissiere', '77140 Moncourt Fromonvile', '0609059399'),
(172, 'Schenkel', 'Stéphane', '16 rue de la note', '75002 Paris', '0612131415'),
(173, 'Weber', 'Irwin', '5 rue de du role', '91100 Corbeil Essonnes', '0645879632'),
(174, 'azaz', 'azaza', 'azazaz', 'azaza', 'zazaza');

-- --------------------------------------------------------

--
-- Structure de la table `concerner`
--

CREATE TABLE IF NOT EXISTS `concerner` (
  `NUM_DEM` int(11) NOT NULL,
  `ARRONDISS_DEM` int(11) NOT NULL,
  PRIMARY KEY (`NUM_DEM`,`ARRONDISS_DEM`),
  KEY `FK_CONCERNER_ARRONDISSEMENT` (`ARRONDISS_DEM`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `concerner`
--

INSERT INTO `concerner` (`NUM_DEM`, `ARRONDISS_DEM`) VALUES
(1, 1),
(1, 2),
(1, 3),
(6, 7),
(6, 8),
(7, 8),
(6, 9),
(5, 11),
(5, 13),
(7, 16);

-- --------------------------------------------------------

--
-- Structure de la table `demandes`
--

CREATE TABLE IF NOT EXISTS `demandes` (
  `NUM_DEM` int(11) NOT NULL,
  `NUM_CLI` int(11) NOT NULL,
  `TYPE_DEM` varchar(128) NOT NULL,
  `DATE_LIMITE` date DEFAULT NULL,
  PRIMARY KEY (`NUM_DEM`),
  KEY `I_FK_DEMANDES_CLIENTS` (`NUM_CLI`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `demandes`
--

INSERT INTO `demandes` (`NUM_DEM`, `NUM_CLI`, `TYPE_DEM`, `DATE_LIMITE`) VALUES
(1, 167, 'F3', '1998-03-01'),
(2, 103, 'F1', '2016-01-15'),
(3, 106, 'F2', '2016-01-01'),
(4, 119, 'F2', '2016-01-15'),
(5, 107, 'F2', '2016-01-14'),
(6, 116, 'F3', '2016-01-16'),
(7, 114, 'F2', '2016-01-22');

-- --------------------------------------------------------

--
-- Structure de la table `locataires`
--

CREATE TABLE IF NOT EXISTS `locataires` (
  `NUMEROLOC` int(11) NOT NULL,
  `NOM_LOC` varchar(128) NOT NULL,
  `PRENOM_LOC` varchar(128) NOT NULL,
  `DATENAISS` date NOT NULL,
  `TEL_LOC` char(255) DEFAULT NULL,
  `R_I_B` int(11) NOT NULL,
  `BANQUE` varchar(128) NOT NULL,
  `RUE_BANQUE` varchar(128) NOT NULL,
  `CODEVILLE_BANQUE` varchar(128) NOT NULL,
  `TEL_BANQUE` char(255) DEFAULT NULL,
  PRIMARY KEY (`NUMEROLOC`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `locataires`
--

INSERT INTO `locataires` (`NUMEROLOC`, `NOM_LOC`, `PRENOM_LOC`, `DATENAISS`, `TEL_LOC`, `R_I_B`, `BANQUE`, `RUE_BANQUE`, `CODEVILLE_BANQUE`, `TEL_BANQUE`) VALUES
(500, 'Code', '500', '0000-00-00', '00 00 00 00', 9999999, '/', '/', '/', '00 00 00 00'),
(501, 'GROSBRAS', 'Rene', '1953-04-12', '43 02 33 83', 2759666, 'BNP', '4 allees des roses', '69000 Lyon', '46 95 10 42'),
(502, 'MACAISSE', 'Raoul', '1959-03-25', '49 01 06 20', 2223654, 'CREDIT LYONNAIS', '6 rue des Halles', '45000 Orléans', '34 69 01 40'),
(504, 'BAHUSSE', 'Maurice', '1967-09-25', '34 50 81 33', 9985959, 'BANQUE POPULAIRE', 'Place de la Gare', '38000 Grenoble', '34 78 46 76'),
(505, 'LAJANTE', 'Bernard', '1957-03-26', '42 87 08 10', 1258741, 'HERVE', '8 rue des Platannes', '63000 Clermont', '64 37 27 70'),
(506, 'DUCRIC', 'Eric', '1958-08-08', '48 90 70 05', 9878777, 'CCP', '45 Av Labellebleue', '06000 Nice', '48 46 71 87'),
(507, 'GRANDRUBAN', 'Antoine', '1959-01-12', NULL, 8546988, 'BNP', '254 Cours Louis Rena', '03000 Moulins', '39 58 55 65'),
(508, 'LATIRE', 'Julie', '1956-11-25', '48 33 05 24', 5556688, 'BNP', '214 Quai A.Durand', '69004 Lyon', '39 86 02 18'),
(509, 'VICTOIR', 'Emile', '1970-02-13', '48 57 64 38', 1231478, 'CREDIT LYONNAIS', '124 Rue de la Montée', '42000 St Etienne', '30 92 59 52'),
(510, 'DELABILLE', 'Arman', '1961-03-12', '60 06 51 09', 1142544, 'CCP', '1 bis Bv de l''Europe', '69005 Lyon', '46 77 34 96'),
(511, 'MILON', 'Pierre', '1965-04-18', '40 38 16 59', 2568997, 'CCP', '17 Bv E Heriot', '69000 Lyon', '47 21 87 87'),
(512, 'MOUILLET', 'Jules', '1947-07-12', '60 75 44 24', 1025982, 'BANQUE POPULAIRE', '4 rue du 18 Juin 44', '38000 Grenoble', '48 87 14 96'),
(513, 'LATTRE', 'Michel', '1951-05-03', '39 61 11 01', 1369250, 'BNP', '18 rue des Freres Ch', '42000 St Etienne', '42 45 03 80'),
(514, 'JURIN', 'Fred', '1952-11-18', '40 84 07 76', 1478520, 'SOCIETE GENERALE', '123 Bv du Port', '69004 Lyon', '48 57 24 62'),
(515, 'BREVENT', 'Robert', '1962-06-02', '48 54 36 18', 2365896, 'CREDIT LYONNAIS', '7 Allee des Crocus', '69120 St Flour', '48 29 45 91'),
(516, 'LATOUR', 'Gerard', '1956-04-04', '42 09 32 15', 4587412, 'CREDIT LYONNAIS', '4 rue du Petit Princ', '38250 La Garenne', '48 38 02 98'),
(517, 'DAMIEN', 'Paul', '1959-05-19', '48 68 01 69', 1236955, 'BANQUE POPULAIRE', 'La Pervenche  Grandi', '69540 Le Tounans', '39 78 80 41'),
(518, 'LUMONET', 'Patrick', '1956-02-14', '34 97 56 96', 2365556, 'PARISBAS', '4 Rue de la Courme', '42990 St Francois', '48 54 83 73'),
(520, 'NOYEUX', 'Joel', '1970-12-25', '46 81 40 84', 1225698, 'SOCIETE GENERALE', '231 Bv Des Bateliers', '38000 Grenoble', '46 87 60 29'),
(521, 'NEUMANN', 'Michel', '1974-06-07', NULL, 25994, 'BNP', '34 rue de Romaiville', '95290 Lisle Adam', '34 69 01 40'),
(522, 'HAMDI', 'Daniel', '1974-07-28', '40 30 37 99', 26532, 'BNP', '48 rue Rosa Bonheur', '78520 Limay', '30 92 59 52'),
(523, 'LAJEMI', 'Pascal', '1974-08-30', '48 70 92 28', 26635, 'CREDIT LYONNAIS', '191 rue des Pyrénées', '78520 Follainville', '34 78 46 76'),
(524, 'LOUET', 'Najia', '1974-09-01', NULL, 26685, 'BANQUE POPULAIRE', '78 rue de l''Amiral Mouchez', '75019 Paris', '42 01 02 19'),
(525, 'MANCEL', 'Lionel', '1974-12-24', '47 00 28 49', 26752, 'BNP', '16 rue de la Rochefoucault', '92000 Nanterre', '46 95 10 42');

-- --------------------------------------------------------

--
-- Structure de la table `proprietaires`
--

CREATE TABLE IF NOT EXISTS `proprietaires` (
  `NUMEROPROP` int(11) NOT NULL,
  `NOM` varchar(255) NOT NULL,
  `PRENOM` varchar(255) NOT NULL,
  `ADRESSE` varchar(255) NOT NULL,
  `CODE_VILLE` varchar(128) NOT NULL,
  `TEL` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`NUMEROPROP`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `proprietaires`
--

INSERT INTO `proprietaires` (`NUMEROPROP`, `NOM`, `PRENOM`, `ADRESSE`, `CODE_VILLE`, `TEL`) VALUES
(1, 'CHOLERA', 'Paul', '1  Lot Le Cedre', '48100  Marvejols', '43 02 33 83'),
(2, 'LAPIN', 'Françoise', '10 Bv G De Gaulle', '13012 Marseille', '49 01 06 20'),
(3, 'BERGER', 'Simone', '100 Bv de l''Europe', '63000 Clermont Ferra', '34 50 81 33'),
(4, 'RAVASIN', 'Hélène', '1108 Bv Charles Nedel', '13006 Marseille', '42 87 08 10'),
(5, 'CALVENNET', 'Patricia', '114 Rue de l''Arsenal', '83000 Toulon', '48 90 70 05'),
(6, 'TAMPALE', 'Danièle', '12 rue grand Meaulne', '23400 Pliure', '44 22 33 11'),
(7, 'GIRAUD', 'Bernadette', '13 rue  Lugano', '13000 Marseille', '48 33 05 24'),
(8, 'CLOAMS', 'Frédérique', '133  Rte Dandune', '30800 Nimes', '48 57 64 38'),
(9, 'GARDINES', 'Jules', '14 Route de la Mazelle', '30000 Nimes', '60 06 51 09'),
(10, 'GUILLETTE', 'Hélène', '14 route du Plateau', '52390 La Maugrette', '40 38 16 59'),
(11, 'GALLYNA', 'Noel', '14 rue de la poste', '42000 St Etienne', '60 75 44 24'),
(12, 'GEURMAT', 'Bernadette', '147 rue Gambetta', '42000 St Etienne', '39 61 11 01'),
(13, 'DELAILA', 'J.Michel', '16 Chemin des Plamines', '82500 Layne/Mer', '40 84 07 76');

-- --------------------------------------------------------

--
-- Structure de la table `visiter`
--

CREATE TABLE IF NOT EXISTS `visiter` (
  `NUM_CLI` int(11) NOT NULL,
  `NUMAPPART` int(11) NOT NULL,
  `DATE_VISITE` date DEFAULT NULL,
  PRIMARY KEY (`NUM_CLI`,`NUMAPPART`),
  KEY `I_FK_VISITER_CLIENTS` (`NUM_CLI`),
  KEY `I_FK_VISITER_APPARTEMENTS` (`NUMAPPART`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `appartements`
--
ALTER TABLE `appartements`
  ADD CONSTRAINT `appartements_ibfk_1` FOREIGN KEY (`NUMEROPROP`) REFERENCES `proprietaires` (`NUMEROPROP`),
  ADD CONSTRAINT `appartements_ibfk_2` FOREIGN KEY (`NUMEROLOC`) REFERENCES `locataires` (`NUMEROLOC`);

--
-- Contraintes pour la table `concerner`
--
ALTER TABLE `concerner`
  ADD CONSTRAINT `concerner_ibfk_1` FOREIGN KEY (`NUM_DEM`) REFERENCES `demandes` (`NUM_DEM`),
  ADD CONSTRAINT `concerner_ibfk_2` FOREIGN KEY (`ARRONDISS_DEM`) REFERENCES `arrondissement` (`ARRONDISS_DEM`);

--
-- Contraintes pour la table `demandes`
--
ALTER TABLE `demandes`
  ADD CONSTRAINT `demandes_ibfk_1` FOREIGN KEY (`NUM_CLI`) REFERENCES `clients` (`NUM_CLI`);

--
-- Contraintes pour la table `visiter`
--
ALTER TABLE `visiter`
  ADD CONSTRAINT `visiter_ibfk_1` FOREIGN KEY (`NUM_CLI`) REFERENCES `clients` (`NUM_CLI`),
  ADD CONSTRAINT `visiter_ibfk_2` FOREIGN KEY (`NUMAPPART`) REFERENCES `appartements` (`NUMAPPART`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
