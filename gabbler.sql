-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : mar. 09 mai 2023 à 10:05
-- Version du serveur : 10.4.21-MariaDB
-- Version de PHP : 8.0.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `gabbler`
--

DROP DATABASE IF EXISTS gabbler;
CREATE DATABASE gabbler;
USE gabbler;

-- --------------------------------------------------------

--
-- Structure de la table `gab`
--

CREATE TABLE `gab` (
  `id` int(11) NOT NULL,
  `content` varchar(255) DEFAULT NULL,
  `post_date` datetime DEFAULT NULL,
  `id_parent_gab` int(11) DEFAULT NULL,
  `uuid_user` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `gab`
--

INSERT INTO `gab` (`id`, `content`, `post_date`, `id_parent_gab`, `uuid_user`) VALUES
(1, 'LE CHEVAL C\'EST TROP GENIAL', '2023-05-09 09:36:26', NULL, '3a87ebf1-85f9-4ad5-ba8f-2ea55bba4917'),
(2, 'VIVE LA REPUBLIQUE', '2023-05-09 09:36:26', NULL, '3a87ebf1-85f9-4ad5-ba8f-2ea55bba4917'),
(3, 'LE COMMENTAIRE OUAIS OUAIS', '2023-05-09 09:46:40', 1, '7b64bc72-ed0a-43cc-8cb5-8014cae763db'),
(4, 'LA CLAQUETTE DE SES MORTS', '2023-05-09 09:46:40', NULL, 'aa5ced03-9e16-4a57-93bd-400671efaa77'),
(5, 'BAHJAHHAHAHA VIVA RUSSIA', '2023-05-24 14:14:04', NULL, 'f9291aac-3a2f-42b8-8060-1ac370dce173'),
(6, 'SUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUU', '2023-05-24 14:14:04', NULL, 'f9291aac-3a2f-42b8-8060-1ac370dce173');

-- --------------------------------------------------------

--
-- Structure de la table `interaction`
--

CREATE TABLE `interaction` (
  `id` int(11) NOT NULL,
  `action` varchar(255) DEFAULT NULL,
  `action_date` datetime DEFAULT NULL,
  `id_gab` int(11) NOT NULL,
  `uuid_user` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `interaction`
--

INSERT INTO `interaction` (`id`, `action`, `action_date`, `id_gab`, `uuid_user`) VALUES
(1, 'LIKE', '2023-05-09 09:57:38', 5, '3a87ebf1-85f9-4ad5-ba8f-2ea55bba4917'),
(2, 'DISLIKE', '2023-05-09 09:57:38', 5, '7b64bc72-ed0a-43cc-8cb5-8014cae763db'),
(3, 'LIKE', '2023-05-09 09:57:38', 4, '3a87ebf1-85f9-4ad5-ba8f-2ea55bba4917'),
(4, 'LIKE', '2023-05-09 09:57:38', 1, 'aa5ced03-9e16-4a57-93bd-400671efaa77'),
(5, 'LIKE', '2023-05-09 09:57:38', 2, 'f9291aac-3a2f-42b8-8060-1ac370dce173');

-- --------------------------------------------------------

--
-- Structure de la table `media`
--

CREATE TABLE `media` (
  `id` int(11) NOT NULL,
  `date` datetime DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `id_gab` int(11) DEFAULT NULL,
  `uuid_user` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `media`
--

INSERT INTO `media` (`id`, `date`, `type`, `url`,`id_gab`, `uuid_user`) VALUES
(1,	'2023-04-24 21:46:04',	'AVATAR',	'yanispp.jpg',	null,	'7b64bc72-ed0a-43cc-8cb5-8014cae763db'),
(2,	'2023-04-24 21:46:04',	'BANNER',	'yanisbanner.jpg',	null,	'7b64bc72-ed0a-43cc-8cb5-8014cae763db'),
(3,	'2023-04-24 22:55:04',	'POST',	'yanisbanner.jpg',	1, null),
(4,	'2023-04-24 22:55:04',	'AVATAR',	'lorispp.jpg',		null, '3a87ebf1-85f9-4ad5-ba8f-2ea55bba4917'),
(6,	'2023-04-24 22:55:04',	'AVATAR',	'nathpp.jpg',	null, 'aa5ced03-9e16-4a57-93bd-400671efaa77'),
(7,	'2023-04-24 21:46:04',	'BANNER',	'lorisbanner.jpg',	null,	'3a87ebf1-85f9-4ad5-ba8f-2ea55bba4917'),
(9,	'2023-04-24 21:46:04',	'BANNER',	'nathbanner.jpg',	null,	'aa5ced03-9e16-4a57-93bd-400671efaa77'),
(10,	'2023-04-24 22:55:04',	'AVATAR',	'jadenpp.jpg',	null,	'f9291aac-3a2f-42b8-8060-1ac370dce173'),
(11,	'2023-04-24 21:46:04',	'BANNER',	'jadenbanner.jpg', null,	'f9291aac-3a2f-42b8-8060-1ac370dce173');

-- --------------------------------------------------------

--
-- Structure de la table `subscription`
--

CREATE TABLE `subscription` (
  `id` int(11) NOT NULL,
  `auto_renewal` bit(1) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `end_date` datetime DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `id_subscription_offer` int(11) NOT NULL,
  `uuid_user` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `subscription_offer`
--

CREATE TABLE `subscription_offer` (
  `id` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `duration` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE `user` (
  `uuid` varchar(255) NOT NULL,
  `auth_token` varchar(255) DEFAULT NULL,
  `biography` varchar(255) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `creation_date` datetime DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `firstname` varchar(255) DEFAULT NULL,
  `is_validated` bit(1) DEFAULT NULL,
  `lastname` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `roles` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`uuid`, `auth_token`, `biography`, `birthday`, `creation_date`, `email`, `firstname`, `is_validated`, `lastname`, `password`, `roles`, `username`) VALUES
('3a87ebf1-85f9-4ad5-ba8f-2ea55bba4917', NULL, 'La biographie de loris le nullos', '2001-05-23', '2023-05-09 09:26:22', 'loris.terry@hesias.fr', 'Loris', b'1', 'Terry', '$2a$10$k.gxB7vM5gG0/p5EKZzrHOB/tMP7132fefb56yxME4Izg1UgJw/va', 'USER', 'LorisTrr'),
('7b64bc72-ed0a-43cc-8cb5-8014cae763db', NULL, 'La biographie de Yanis le bg', '2001-07-28', '2023-05-09 09:25:08', 'yanis.houdier@hesias.fr', 'Yanis', b'1', 'Houdier', '$2a$10$j59QOOvi5rIDjTDBCsx.quuIeIjRuLKP1wLZcOqX1iCJ.dO/e6rXa', 'USER,ADMIN', 'Sinsay'),
('aa5ced03-9e16-4a57-93bd-400671efaa77', NULL, 'La biographie de nath le nullos', '1998-12-15', '2023-05-09 09:25:54', 'nathim.richard@hesias.fr', 'Nathim', b'1', 'Richard', '$2a$10$67MYpT9vmjSogZFC7dg5x.J9ECpq4W0HWfwBF2DoPVIQ3HbbMkLIC', 'USER', 'Nath'),
('f9291aac-3a2f-42b8-8060-1ac370dce173', NULL, 'La biographie de jaden le nullos', '2001-08-23', '2023-05-09 09:25:59', 'jaden.couchot@hesias.fr', 'Jaden', b'1', 'Couchot', '$2a$10$LxrcjKO9HgjtD63GDFyRRuOXBrKYWEP99uV5QFNV4fzq.VlPWkS0m', 'USER', 'KebabCouchot');

-- --------------------------------------------------------

--
-- Structure de la table `user_relationships`
--

CREATE TABLE `user_relationships` (
  `id` int(11) NOT NULL,
  `date` datetime DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `uuid_user` varchar(255) NOT NULL,
  `uuid_user_related` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `user_relationships`
--

INSERT INTO `user_relationships` (`id`, `date`, `type`, `uuid_user`, `uuid_user_related`) VALUES
(1, '2023-05-09 10:04:13', 'BLOCKED', '3a87ebf1-85f9-4ad5-ba8f-2ea55bba4917', '7b64bc72-ed0a-43cc-8cb5-8014cae763db'),
(3, '2023-05-09 10:04:13', 'AUTHORIZED', '3a87ebf1-85f9-4ad5-ba8f-2ea55bba4917', 'aa5ced03-9e16-4a57-93bd-400671efaa77'),
(4, '2023-05-09 10:05:06', 'BLOCKED', 'f9291aac-3a2f-42b8-8060-1ac370dce173', '3a87ebf1-85f9-4ad5-ba8f-2ea55bba4917');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `gab`
--
ALTER TABLE `gab`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKbwuru8ms8xk16u00fnyvgmbcg` (`id_parent_gab`),
  ADD KEY `FK8yhe8uoodia83h4fu75c9lofc` (`uuid_user`);

--
-- Index pour la table `interaction`
--
ALTER TABLE `interaction`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKdpnhb6csbv4ofvtthldxbamo9` (`uuid_user`,`id_gab`),
  ADD KEY `FKqbaijui9swkqgb9lldc765swc` (`id_gab`);

--
-- Index pour la table `media`
--
ALTER TABLE `media`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKd8jlvle86fw7f7gr9j96t0l5h` (`uuid_user`,`type`),
  ADD KEY `FKqiemsgo2d5uyp9n5bity1bims` (`id_gab`);

--
-- Index pour la table `subscription`
--
ALTER TABLE `subscription`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKp5vdiuj9ncekf6ts89exypms` (`id_subscription_offer`),
  ADD KEY `FKe4hyhu6gutm50cdy0arwidhod` (`uuid_user`);

--
-- Index pour la table `subscription_offer`
--
ALTER TABLE `subscription_offer`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`uuid`),
  ADD UNIQUE KEY `UKsb8bbouer5wak8vyiiy4pf2bx` (`username`),
  ADD UNIQUE KEY `UKob8kqyqqgmefl0aco34akdtpe` (`email`);

--
-- Index pour la table `user_relationships`
--
ALTER TABLE `user_relationships`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKdl5afgtmnwtas5g31pl21qe5y` (`uuid_user`),
  ADD KEY `FKqhyhd5yxn2mdedbio4qc56ioo` (`uuid_user_related`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `gab`
--
ALTER TABLE `gab`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT pour la table `interaction`
--
ALTER TABLE `interaction`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT pour la table `media`
--
ALTER TABLE `media`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `subscription`
--
ALTER TABLE `subscription`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `subscription_offer`
--
ALTER TABLE `subscription_offer`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `user_relationships`
--
ALTER TABLE `user_relationships`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `gab`
--
ALTER TABLE `gab`
  ADD CONSTRAINT `FK8yhe8uoodia83h4fu75c9lofc` FOREIGN KEY (`uuid_user`) REFERENCES `user` (`uuid`),
  ADD CONSTRAINT `FKbwuru8ms8xk16u00fnyvgmbcg` FOREIGN KEY (`id_parent_gab`) REFERENCES `gab` (`id`);

--
-- Contraintes pour la table `interaction`
--
ALTER TABLE `interaction`
  ADD CONSTRAINT `FK4mp30alx5hvf9ial3kebc8r2x` FOREIGN KEY (`uuid_user`) REFERENCES `user` (`uuid`),
  ADD CONSTRAINT `FKqbaijui9swkqgb9lldc765swc` FOREIGN KEY (`id_gab`) REFERENCES `gab` (`id`);

--
-- Contraintes pour la table `media`
--
ALTER TABLE `media`
  ADD CONSTRAINT `FKn6me9bmymgbjwgpfjx8ovy67x` FOREIGN KEY (`uuid_user`) REFERENCES `user` (`uuid`),
  ADD CONSTRAINT `FKqiemsgo2d5uyp9n5bity1bims` FOREIGN KEY (`id_gab`) REFERENCES `gab` (`id`);

--
-- Contraintes pour la table `subscription`
--
ALTER TABLE `subscription`
  ADD CONSTRAINT `FKe4hyhu6gutm50cdy0arwidhod` FOREIGN KEY (`uuid_user`) REFERENCES `user` (`uuid`),
  ADD CONSTRAINT `FKp5vdiuj9ncekf6ts89exypms` FOREIGN KEY (`id_subscription_offer`) REFERENCES `subscription_offer` (`id`);

--
-- Contraintes pour la table `user_relationships`
--
ALTER TABLE `user_relationships`
  ADD CONSTRAINT `FKdl5afgtmnwtas5g31pl21qe5y` FOREIGN KEY (`uuid_user`) REFERENCES `user` (`uuid`),
  ADD CONSTRAINT `FKqhyhd5yxn2mdedbio4qc56ioo` FOREIGN KEY (`uuid_user_related`) REFERENCES `user` (`uuid`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
