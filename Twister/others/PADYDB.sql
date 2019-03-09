-- phpMyAdmin SQL Dump
-- version 4.6.6deb4
-- https://www.phpmyadmin.net/
--
-- Client :  localhost:3306
-- Généré le :  Lun 04 Mars 2019 à 15:37
-- Version du serveur :  5.7.25
-- Version de PHP :  7.0.33-0+deb9u1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `PADYDB`
--

-- --------------------------------------------------------

--
-- Structure de la table `DeadKeys`
--

CREATE TABLE `DeadKeys` (
  `clef` varchar(42) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `DeadKeys`
--

INSERT INTO `DeadKeys` (`clef`) VALUES
('10578222'),
('875828612'),
('1510591922'),
('1699589302'),
('256348269'),
('144302188'),
('430073490'),
('1959318806'),
('1975032039'),
('1259060707');

-- --------------------------------------------------------

--
-- Structure de la table `Friends`
--

CREATE TABLE `Friends` (
  `idfrom` int(10) UNSIGNED NOT NULL,
  `idto` int(10) UNSIGNED NOT NULL,
  `date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `Sessions`
--

CREATE TABLE `Sessions` (
  `id` int(10) UNSIGNED NOT NULL,
  `clef` varchar(64) DEFAULT NULL,
  `start` varchar(64) DEFAULT NULL,
  `finish` varchar(64) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `Sessions`
--

INSERT INTO `Sessions` (`id`, `clef`, `start`, `finish`) VALUES
(12, '1131399185', '1551710091057', '1551728145277'),
(13, NULL, NULL, NULL),
(14, '164979483', '1551710091145', '1551728091207'),
(15, NULL, NULL, NULL),
(16, NULL, NULL, NULL),
(17, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `Users`
--

CREATE TABLE `Users` (
  `id` int(10) UNSIGNED NOT NULL,
  `login` varchar(42) NOT NULL,
  `password` blob NOT NULL,
  `nom` varchar(42) DEFAULT NULL,
  `prenom` varchar(42) DEFAULT NULL,
  `mail` varchar(42) NOT NULL,
  `sexe` varchar(4) DEFAULT 'U'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `Users`
--

INSERT INTO `Users` (`id`, `login`, `password`, `nom`, `prenom`, `mail`, `sexe`) VALUES
(12, 'Bouddah', 0x6465757331323334, 'NLove', 'Peace', 'bouddahofficial@paradis.eden', 'u'),
(13, 'Quetzal', 0x6465757331323334, 'Serpent', 'Dieu', 'aztecs4ever@paradis.eden', 'u'),
(14, 'Satan', 0x6465757331323334, 'Malin', 'Diable', 'angedechu666@enfers.hell', 'u'),
(15, 'Dieu3', 0x616e6369656e74657374616d656e74, 'machina', 'deuxex', 'lestreizecommandements@paradis.eden', 'u'),
(16, 'Dieu4', 0x616e6369656e74657374616d656e74, 'machina', 'deuxex', 'lesquatorzecommandements@paradis.eden', 'u'),
(17, 'Dieu6', 0x616e6369656e74657374616d656e74, 'machina', 'deuxex', 'lesseizescommandements@paradis.eden', 'u');

--
-- Index pour les tables exportées
--

--
-- Index pour la table `Friends`
--
ALTER TABLE `Friends`
  ADD PRIMARY KEY (`idfrom`,`idto`),
  ADD KEY `c3` (`idto`);

--
-- Index pour la table `Sessions`
--
ALTER TABLE `Sessions`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `Users`
--
ALTER TABLE `Users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `login` (`login`),
  ADD UNIQUE KEY `mail` (`mail`);

--
-- AUTO_INCREMENT pour les tables exportées
--

--
-- AUTO_INCREMENT pour la table `Users`
--
ALTER TABLE `Users`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;
--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `Friends`
--
ALTER TABLE `Friends`
  ADD CONSTRAINT `c2` FOREIGN KEY (`idfrom`) REFERENCES `Users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `c3` FOREIGN KEY (`idto`) REFERENCES `Users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `Sessions`
--
ALTER TABLE `Sessions`
  ADD CONSTRAINT `c1` FOREIGN KEY (`id`) REFERENCES `Users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
