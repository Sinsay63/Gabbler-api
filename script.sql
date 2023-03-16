#------------------------------------------------------------
#        Script MySQL.
#------------------------------------------------------------
DROP DATABASE IF EXISTS gabbler;
CREATE DATABASE gabbler;
USE gabbler;


#------------------------------------------------------------
# Table: media
#------------------------------------------------------------

CREATE TABLE media
(
    id         Int Auto_increment NOT NULL,
    path_media Varchar(255)       NOT NULL,
    created_at Datetime           NOT NULL,
    CONSTRAINT media_PK PRIMARY KEY (id)
) ENGINE = InnoDB;


#------------------------------------------------------------
# Table: user
#------------------------------------------------------------

CREATE TABLE user
(
    id           Int Auto_increment NOT NULL,
    username     Varchar(50)        NOT NULL,
    firstname    Varchar(50)        NOT NULL,
    lastname     Varchar(50)        NOT NULL,
    birthday     Date               NOT NULL,
    email        Varchar(50)        NOT NULL,
    password     Varchar(50)        NOT NULL,
    auth_token   Varchar(255)       NOT NULL,
    biography    Varchar(255)       NOT NULL,
    created_at   Datetime           NOT NULL,
    is_validated Bool               NOT NULL,
    id_media     Int,
    CONSTRAINT user_PK PRIMARY KEY (id),
    CONSTRAINT user_media_FK FOREIGN KEY (id_media) REFERENCES media (id)
) ENGINE = InnoDB;


#------------------------------------------------------------
# Table: gab
#------------------------------------------------------------

CREATE TABLE gab
(
    id        Int Auto_increment NOT NULL,
    content   Varchar(255)       NOT NULL,
    post_date Datetime           NOT NULL,
    id_user   Int,
    CONSTRAINT gab_PK PRIMARY KEY (id),
    CONSTRAINT gab_user_FK FOREIGN KEY (id_user) REFERENCES user (id)
) ENGINE = InnoDB;


#------------------------------------------------------------
# Table: interaction
#------------------------------------------------------------

CREATE TABLE interaction
(
    id          Int Auto_increment      NOT NULL,
    action      Enum ("like","dislike") NOT NULL,
    action_date Datetime                NOT NULL,
    id_gab      Int                     NOT NULL,
    id_user     Int,
    CONSTRAINT interaction_PK PRIMARY KEY (id),
    CONSTRAINT interaction_gab_FK FOREIGN KEY (id_gab) REFERENCES gab (id),
    CONSTRAINT interaction_user0_FK FOREIGN KEY (id_user) REFERENCES user (id)
) ENGINE = InnoDB;


#------------------------------------------------------------
# Table: user_relationships
#------------------------------------------------------------

CREATE TABLE user_relationships
(
    id              Int Auto_increment            NOT NULL,
    typeEnum        Enum ("blocked","authorized") NOT NULL,
    date            DateTime                      NOT NULL,
    id_user         Int                           NOT NULL,
    id_user_related Int                           NOT NULL,
    CONSTRAINT user_relationships_PK PRIMARY KEY (id),
    CONSTRAINT user_relationships_user_FK FOREIGN KEY (id_user) REFERENCES user (id),
    CONSTRAINT user_relationships_user0_FK FOREIGN KEY (id_user_related) REFERENCES user (id)
) ENGINE = InnoDB;


#------------------------------------------------------------
# Table: subscription_offer
#------------------------------------------------------------

CREATE TABLE subscription_offer
(
    id                 Int Auto_increment                           NOT NULL,
    name               Varchar(50)                                  NOT NULL,
    description        Varchar(255)                                 NOT NULL,
    price              Float                                        NOT NULL,
    duration_in_months Enum ("1 mois","3 mois","6 mois", "12 mois") NOT NULL,
    CONSTRAINT subscription_offer_PK PRIMARY KEY (id)
) ENGINE = InnoDB;


#------------------------------------------------------------
# Table: subscription
#------------------------------------------------------------

CREATE TABLE subscription
(
    id                    Int Auto_increment NOT NULL,
    created_at            Datetime           NOT NULL,
    updated_at            Datetime           NOT NULL,
    start_date            Datetime           NOT NULL,
    end_date              Datetime           NOT NULL,
    auto_renewable        Bool               NOT NULL,
    id_subscription_offer Int                NOT NULL,
    id_user               Int                NOT NULL,
    CONSTRAINT subscription_PK PRIMARY KEY (id),
    CONSTRAINT subscription_subscription_offer_FK FOREIGN KEY (id_subscription_offer) REFERENCES subscription_offer (id),
    CONSTRAINT subscription_user0_FK FOREIGN KEY (id_user) REFERENCES user (id),
    CONSTRAINT subscription_user_AK UNIQUE (id_user)
) ENGINE = InnoDB;


#------------------------------------------------------------
# Table: subscription_history
#------------------------------------------------------------

CREATE TABLE subscription_history
(
    id                 Int         NOT NULL,
    start_date         Datetime    NOT NULL,
    end_date           Datetime    NOT NULL,
    auto_renewable     Bool        NOT NULL,
    created_at         Datetime    NOT NULL,
    offer_name         Varchar(50) NOT NULL,
    duration_in_months Int         NOT NULL,
    price              Float       NOT NULL,
    username           Varchar(50) NOT NULL,
    email              Varchar(50) NOT NULL
) ENGINE = InnoDB;


#------------------------------------------------------------
# Table: gab_comments
#------------------------------------------------------------

CREATE TABLE gab_comments
(
    id     Int NOT NULL,
    id_gab Int NOT NULL,
    CONSTRAINT gab_comments_PK PRIMARY KEY (id, id_gab),
    CONSTRAINT gab_comments_gab_FK FOREIGN KEY (id) REFERENCES gab (id),
    CONSTRAINT gab_comments_gab0_FK FOREIGN KEY (id_gab) REFERENCES gab (id)
) ENGINE = InnoDB;


#------------------------------------------------------------
# Table: gab_media
#------------------------------------------------------------

CREATE TABLE gab_media
(
    id     Int NOT NULL,
    id_gab Int NOT NULL,
    CONSTRAINT gab_media_PK PRIMARY KEY (id, id_gab),
    CONSTRAINT gab_media_media_FK FOREIGN KEY (id) REFERENCES media (id),
    CONSTRAINT gab_media_gab0_FK FOREIGN KEY (id_gab) REFERENCES gab (id)
) ENGINE = InnoDB;

INSERT INTO `user` (`id`, `username`, `firstname`, `lastname`, `birthday`, `email`, `password`, `auth_token`,
                    `biography`, `created_at`, `is_validated`, `id_media`)
VALUES (NULL, 'Sinsay', 'Yanis', 'HOUDIER', '2001-07-28', 'yanis.houdier@gmail.com', 'password', '',
        'JE SUIS UN HOMME TOUT A FAIT COMBLÉ', '2023-03-09 16:00:10.000000', '1', NULL),
       (NULL, 'LorisTrr', 'Loris', 'Terry', '2001-12-25', 'loris.jesuisnularocketleague@hesias.org', 'password', '',
        'QUI VEUT UN SANDWITCH TRIANGLE ?', '2023-03-09 16:00:10.000000', '1', NULL);

INSERT INTO `user_relationships` (`id`, `typeEnum`, `date`, `id_user`, `id_user_related`)
VALUES (NULL, 'blocked', '2023-03-09 17:08:10.000000', '2', '1'),
       (NULL, 'blocked', '2023-03-09 17:08:10.000000', '1', '2');

INSERT INTO `subscription_offer` (`id`, `name`, `description`, `price`, `duration_in_months`)
VALUES (NULL, 'Abonnement 1 mois',
        'Cet abonnement a pour durée 1 mois, une fois l\'échéance atteinte, l\'abonnement sera renouvelé automatiquement sauf action de votre part.',
        '5', '1 mois'),
       (NULL, 'Abonnement 3 mois',
        'Cet abonnement a pour durée 3 mois, une fois l\'échéance atteinte, l\'abonnement sera renouvelé automatiquement sauf action de votre part.',
        '13.5', '3 mois'),
       (NULL, 'Abonnement 6 mois',
        'Cet abonnement a pour durée 6 mois, une fois l\'échéance atteinte, l\'abonnement sera renouvelé automatiquement sauf action de votre part.',
        '27.5', '6 mois'),
       (NULL, 'Abonnement 12 mois',
        'Cet abonnement a pour durée 1 mois, une fois l\'échéance atteinte, l\'abonnement sera renouvelé automatiquement sauf action de votre part.',
        '55', '12 mois');

INSERT INTO `subscription` (`id`, `start_date`, `end_date`, `auto_renewable`, `created_at`, `updated_at`,
                            `id_subscription_offer`, `id_user`)
VALUES (NULL, '2023-03-09 16:08:18.000000', '2023-04-09 16:08:18', '1', '2023-03-09 16:08:18.000000',
        '2023-03-09 16:08:18.000000', '1', '1'),
       (NULL, '2023-03-09 16:08:18.000000', '2023-06-09 16:08:18', '1', '2023-03-09 16:08:18.000000',
        '2023-03-09 16:08:18.000000', '2', '2');

INSERT INTO `gab` (`id`, `content`, `post_date`, `id_user`)
VALUES (NULL, 'Comme a dit un physicien célèbre : QUOICOUBEH', '2023-03-09 16:09:20.000000', '2'),
       (NULL, 'PTDRRRR LES MECS AVEC DES CALVASSE TRO LA HONTE', '2023-03-09 16:09:20.000000', '1'),
       (NULL, 'TK78 A L\'ENVERS CA FAIT ... ---> ABONNEZ VOUS POUR LA PARTIE 2', '2023-03-09 16:09:20.000000', '1');

INSERT INTO `gab_comments` (`id`, `id_gab`)
VALUES ('1', '2'),
       ('2', '3');

INSERT INTO `interaction` (`id`, `action`, `action_date`, `id_gab`, `id_user`)
VALUES (NULL, 'like', '2023-03-09 16:13:57.000000', '2', '2'),
       (NULL, 'dislike', '2023-03-09 16:13:57.000000', '1', '1');

ALTER TABLE `gabbler`.`user_relationships`
    ADD UNIQUE `UNIQUE_idUser_idUserRelated` (`id_user`, `id_user_related`);

ALTER TABLE `gabbler`.`interaction`
    ADD UNIQUE `UNIQUE_idUser_idGab` (`id_user`, `id_gab`);