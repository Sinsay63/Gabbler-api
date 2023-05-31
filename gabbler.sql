DROP DATABASE IF EXISTS gabbler;
CREATE DATABASE gabbler;
USE gabbler;




CREATE TABLE user
(
    `uuid`          varchar(255) NOT NULL,
    `auth_token`    varchar(255) DEFAULT NULL,
    `biography`     varchar(255) DEFAULT NULL,
    `birthday`      date         DEFAULT NULL,
    `creation_date` datetime     DEFAULT NULL,
    `email`         varchar(255) DEFAULT NULL,
    `firstname`     varchar(255) DEFAULT NULL,
    `is_validated`  bit(1)       DEFAULT NULL,
    `lastname`      varchar(255) DEFAULT NULL,
    `password`      varchar(255) DEFAULT NULL,
    `roles`         varchar(255) DEFAULT NULL,
    `username`      varchar(255) DEFAULT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

ALTER TABLE user
    ADD PRIMARY KEY (`uuid`),
    ADD UNIQUE KEY `UKsb8bbouer5wak8vyiiy4pf2bx` (`username`),
    ADD UNIQUE KEY `UKob8kqyqqgmefl0aco34akdtpe` (`email`);



CREATE TABLE gab
(
    `id`            int(11)      NOT NULL PRIMARY KEY,
    `content`       varchar(255) DEFAULT NULL,
    `post_date`     datetime     DEFAULT NULL,
    `id_parent_gab` int(11)      DEFAULT NULL,
    `uuid_user`     varchar(255) NOT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;


ALTER TABLE gab
    ADD CONSTRAINT `FKbwuru8ms8xk16u00fnyvgmbcg` FOREIGN KEY (id_parent_gab) REFERENCES gab (id),
    ADD CONSTRAINT `FK8yhe8uoodia83h4fu75c9lofc` FOREIGN KEY (uuid_user) REFERENCES user (uuid);
    



CREATE TABLE interaction
(
    `id`          int(11)      NOT NULL PRIMARY KEY,
    `action`      varchar(255) DEFAULT NULL,
    `action_date` datetime     DEFAULT NULL,
    `id_gab`      int(11)      NOT NULL,
    `uuid_user`   varchar(255) NOT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

ALTER TABLE interaction
    ADD UNIQUE KEY `UKdpnhb6csbv4ofvtthldxbamo9` (`uuid_user`,
                                                  `id_gab`),
    ADD CONSTRAINT `FK4mp30alx5hvf9ial3kebc8r2x` FOREIGN KEY (`uuid_user`) REFERENCES `user` (`uuid`),
    ADD CONSTRAINT `FKqbaijui9swkqgb9lldc765swc` FOREIGN KEY (`id_gab`) REFERENCES `gab` (`id`);



CREATE TABLE media
(
    `id`            int(11)      NOT NULL PRIMARY KEY,
    `creation_date` datetime     DEFAULT NULL,
    `type`          varchar(255) DEFAULT NULL,
    `url`           varchar(255) DEFAULT NULL,
    `id_gab`        int(11)      DEFAULT NULL,
    `uuid_user`     varchar(255) DEFAULT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

ALTER TABLE media
    ADD UNIQUE KEY `UKd8jlvle86fw7f7gr9j96t0l5h` (`uuid_user`,
                                                  `type`),
    ADD CONSTRAINT `FKn6me9bmymgbjwgpfjx8ovy67x` FOREIGN KEY (`uuid_user`) REFERENCES `user` (`uuid`),
    ADD CONSTRAINT `FKqiemsgo2d5uyp9n5bity1bims` FOREIGN KEY (`id_gab`) REFERENCES `gab` (`id`);



CREATE TABLE subscription_offer
(
    `id`          int(11) NOT NULL PRIMARY KEY,
    `description` varchar(255) DEFAULT NULL,
    `duration`    varchar(255) DEFAULT NULL,
    `name`        varchar(255) DEFAULT NULL,
    `price`       float        DEFAULT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;


CREATE TABLE subscription
(
    `id`                    int(11)      NOT NULL PRIMARY KEY,
    `creation_date`         datetime DEFAULT NULL,
    `end_date`              datetime DEFAULT NULL,
    `start_date`            datetime DEFAULT NULL,
    `updated_at`            datetime DEFAULT NULL,
    `id_subscription_offer` int(11)      NOT NULL,
    `uuid_user`             varchar(255) NOT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;


ALTER TABLE subscription
    ADD CONSTRAINT `FKe4hyhu6gutm50cdy0arwidhod` FOREIGN KEY (`uuid_user`) REFERENCES `user` (`uuid`),
    ADD CONSTRAINT `FKp5vdiuj9ncekf6ts89exypms` FOREIGN KEY (`id_subscription_offer`) REFERENCES `subscription_offer` (`id`);



CREATE TABLE user_relationships
(
    `id`                int(11)      NOT NULL PRIMARY KEY,
    `creation_date`     datetime     DEFAULT NULL,
    `type`              varchar(255) DEFAULT NULL,
    `uuid_user`         varchar(255) NOT NULL,
    `uuid_user_related` varchar(255) NOT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

ALTER TABLE user_relationships
    ADD CONSTRAINT `FKdl5afgtmnwtas5g31pl21qe5y` FOREIGN KEY (`uuid_user`) REFERENCES `user` (`uuid`),
    ADD CONSTRAINT `FKqhyhd5yxn2mdedbio4qc56ioo` FOREIGN KEY (`uuid_user_related`) REFERENCES `user` (`uuid`);




INSERT INTO `user` (`uuid`,
                    `auth_token`,
                    `biography`,
                    `birthday`,
                    `creation_date`,
                    `email`,
                    `firstname`,
                    `is_validated`,
                    `lastname`,
                    `password`,
                    `roles`,
                    `username`)
VALUES ('3a87ebf1-85f9-4ad5-ba8f-2ea55bba4917',
        NULL,
        'La vie est une branche qui se fane au fil du temps',
        '2001-05-23',
        '2023-05-09 09:26:22',
        'loris.terry@hesias.fr',
        'Loris',1,
        'Terry',
        '$2a$10$k.gxB7vM5gG0/p5EKZzrHOB/tMP7132fefb56yxME4Izg1UgJw/va',
        'USER',
        'LorisTrr'),
       ('7b64bc72-ed0a-43cc-8cb5-8014cae763db',
        NULL,
        'Moi je sais pas vous, mais vive l été',
        '2001-07-28',
        '2023-05-09 09:25:08',
        'yanis.houdier@hesias.fr',
        'Yanis', 1,
        'Houdier',
        '$2a$10$j59QOOvi5rIDjTDBCsx.quuIeIjRuLKP1wLZcOqX1iCJ.dO/e6rXa',
        'USER,PREMIUM',
        'Sinsay'),
       ('aa5ced03-9e16-4a57-93bd-400671efaa77',
        NULL,
        'Pourquoi les gens sont si méchants ?',
        '1998-12-15',
        '2023-05-09 09:25:54',
        'nathim.richard@hesias.fr',
        'Nathim',1,
        'Richard',
        '$2a$10$67MYpT9vmjSogZFC7dg5x.J9ECpq4W0HWfwBF2DoPVIQ3HbbMkLIC',
        'USER', 'Nath'),
       ('f9291aac-3a2f-42b8-8060-1ac370dce173',
        NULL,
        'Vive les voitures qui font vroum vroum',
        '2001-08-23',
        '2023-05-09 09:25:59',
        'jaden.couchot@hesias.fr',
        'Jaden',
        1,
        'Couchot',
        '$2a$10$LxrcjKO9HgjtD63GDFyRRuOXBrKYWEP99uV5QFNV4fzq.VlPWkS0m',
        'USER',
        'KebabCouchot');


INSERT INTO `gab` (`id`,
                   `content`,
                   `post_date`,
                   `id_parent_gab`,
                   `uuid_user`)
VALUES (1,
        'Vivement la fin du confinement ! Je suis à bout',
        '2023-05-09 09:36:26',
        NULL,
        '3a87ebf1-85f9-4ad5-ba8f-2ea55bba4917'),
       (2,
        'L été arrive enfin ! Bon sang que ça fait du bien !',
        '2023-05-09 09:36:26',
        NULL,
        '3a87ebf1-85f9-4ad5-ba8f-2ea55bba4917'),
       (3,
        'Je suis tout à fait d accord avec toi. Je suis impatient de pouvoir sortir et profiter du soleil',
        '2023-05-09 09:46:40',
        1,
        '7b64bc72-ed0a-43cc-8cb5-8014cae763db'),
       (4,
        'Le ski c est comme le roller, mais avec des montagnes.',
        '2023-05-09 09:46:40',
        NULL,
        'aa5ced03-9e16-4a57-93bd-400671efaa77'),
       (5,
        'Mais en fait, la programmation c est trop cool !',
        '2023-05-24 14:14:04',
        NULL,
        'f9291aac-3a2f-42b8-8060-1ac370dce173'),
       (6,
        'Et surtout quand on comprend ce qu on fait^^',
        '2023-05-24 14:21:55',
        5,
        'f9291aac-3a2f-42b8-8060-1ac370dce173'),
        (7,
        'Et surtout quand on comprend ce qu on fait^^',
        '2023-05-24 15:24:04',
        5,
        '7b64bc72-ed0a-43cc-8cb5-8014cae763db');



INSERT INTO `user_relationships` (`id`,
                                  `creation_date`,
                                  `type`,
                                  `uuid_user`,
                                  `uuid_user_related`)
VALUES (1,
        '2023-05-09 10:04:13',
        'BLOCKED',
        '3a87ebf1-85f9-4ad5-ba8f-2ea55bba4917',
        '7b64bc72-ed0a-43cc-8cb5-8014cae763db'),
       (3,
        '2023-05-09 10:44:19',
        'FOLLOWED',
        '3a87ebf1-85f9-4ad5-ba8f-2ea55bba4917',
        'aa5ced03-9e16-4a57-93bd-400671efaa77'),
       (4,
        '2023-05-09 11:05:06',
        'FOLLOWED',
        'f9291aac-3a2f-42b8-8060-1ac370dce173',
        '3a87ebf1-85f9-4ad5-ba8f-2ea55bba4917'),
        (5,
        '2023-05-09 11:05:15',
        'FOLLOWED',
        '7b64bc72-ed0a-43cc-8cb5-8014cae763db',
        '3a87ebf1-85f9-4ad5-ba8f-2ea55bba4917');

INSERT INTO `interaction` (`id`,
                           `action`,
                           `action_date`,
                           `id_gab`,
                           `uuid_user`)
VALUES (1, 'LIKE',
        '2023-05-08 09:57:38',
        1,
        '3a87ebf1-85f9-4ad5-ba8f-2ea55bba4917'),
       (2, 'DISLIKE',
        '2023-05-08 11:55:18',
        1,
        '7b64bc72-ed0a-43cc-8cb5-8014cae763db'),
       (3, 'LIKE',
        '2023-05-08 11:57:38',
        2,
        '3a87ebf1-85f9-4ad5-ba8f-2ea55bba4917'),
       (4, 'LIKE',
        '2023-05-08 16:10:11',
        1,
        'aa5ced03-9e16-4a57-93bd-400671efaa77'),
       (5, 'LIKE',
        '2023-05-09 15:35:32',
        2,
        'f9291aac-3a2f-42b8-8060-1ac370dce173'),
        (6, 'LIKE',
        '2023-05-09 17:25:38',
        3,
        'f9291aac-3a2f-42b8-8060-1ac370dce173'),
        (7, 'DISLIKE',
        '2023-05-09 19:57:38',
        1,
        'f9291aac-3a2f-42b8-8060-1ac370dce173');


INSERT INTO `media` (`id`,
                     `creation_date`,
                     `type`,
                     `url`,
                     `id_gab`,
                     `uuid_user`)
VALUES (1,
        '2023-04-24 21:46:04',
        'AVATAR',
        'yanispp.jpg',
        null,
        '7b64bc72-ed0a-43cc-8cb5-8014cae763db'),
       (2,
        '2023-04-24 21:46:04',
        'BANNER',
        'yanisbanner.jpg',
        null,
        '7b64bc72-ed0a-43cc-8cb5-8014cae763db'),
       (3,
        '2023-04-24 22:55:04',
        'POST',
        'yanisbanner.jpg',
        1, null),
       (4,
        '2023-04-24 22:55:04',
        'AVATAR',
        'lorispp.jpg',
        null,
        '3a87ebf1-85f9-4ad5-ba8f-2ea55bba4917'),
       (5,
        '2023-04-24 22:55:04',
        'AVATAR',
        'nathpp.jpg',
        null,
        'aa5ced03-9e16-4a57-93bd-400671efaa77'),
       (6,
        '2023-04-24 21:46:04',
        'BANNER',
        'lorisbanner.jpg',
        null,
        '3a87ebf1-85f9-4ad5-ba8f-2ea55bba4917'),
       (7,
        '2023-04-24 21:46:04',
        'BANNER',
        'nathbanner.jpg',
        null,
        'aa5ced03-9e16-4a57-93bd-400671efaa77'),
       (8,
        '2023-04-24 22:55:04',
        'AVATAR',
        'jadenpp.jpg',
        null,
        'f9291aac-3a2f-42b8-8060-1ac370dce173'),
       (9,
        '2023-04-24 21:46:04',
        'BANNER',
        'jadenbanner.jpg',
        null,
        'f9291aac-3a2f-42b8-8060-1ac370dce173');


INSERT INTO `subscription_offer` (`id`,
                                  `description`,
                                  `duration`,
                                  `name`,
                                  `price`)
VALUES (1,
        'Abonnement à Gabbler Prestige pour 1 mois',
        'UN_MOIS',
        'Abonnement 1 mois',
        '5'),
       (2,
        'Abonnement à Gabbler Prestige pour 3 mois',
        'TROIS_MOIS',
        'Abonnement 3 mois',
        '13.5'),
       (3,
        'Abonnement à Gabbler Prestige pour 6 mois',
        'SIX_MOIS',
        'Abonnement 6 mois',
        '25.5'),
       (4,
        'Abonnement à Gabbler Prestige pour 12 mois',
        'DOUZE_MOIS',
        'Abonnement 12 mois',
        '48');


INSERT INTO `subscription` (`id`, 
`creation_date`, 
`end_date`, 
`start_date`, 
`updated_at`, 
`id_subscription_offer`, 
`uuid_user`) 
VALUES (1,
        '2023-05-31 16:05:29', 
        '2023-06-30 16:05:29', 
        '2023-05-31 16:05:29', 
        '2023-05-31 16:05:29', 
        1,
        '7b64bc72-ed0a-43cc-8cb5-8014cae763db');




