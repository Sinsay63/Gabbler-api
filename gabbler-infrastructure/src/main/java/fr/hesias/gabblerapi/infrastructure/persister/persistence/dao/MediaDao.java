package fr.hesias.gabblerapi.infrastructure.persister.persistence.dao;

import fr.hesias.gabblerapi.infrastructure.persister.persistence.entity.Media;
import fr.hesias.gabblerapi.infrastructure.persister.persistence.entity.User;
import fr.hesias.gabblerapi.infrastructure.persister.persistence.model.MediaTypeEnum;
import fr.hesias.gabblerapi.infrastructure.persister.persistence.repository.MediaRepository;

import java.util.List;

public class MediaDao
{

    private final MediaRepository mediaRepository;

    public MediaDao(final MediaRepository mediaRepository)
    {

        super();
        this.mediaRepository = mediaRepository;
    }

    public List<Media> getMediaAvatarAndBannerByUserUuid(String userUuid)
    {

        return mediaRepository.findAllByUser_UuidAndTypeIsNot(userUuid, MediaTypeEnum.POST);
    }

    public List<Media> getMediaByGabId(int gabId)
    {

        return mediaRepository.findAllByGab_IdAndTypeIs(gabId, MediaTypeEnum.POST);
    }

    public void addDefaultUserMedias(String userUuid)
    {

        Media avatar = new Media();
        avatar.setUrl("avatar.png");
        avatar.setType(MediaTypeEnum.AVATAR);
        avatar.setUser(new User(userUuid));
        mediaRepository.save(avatar);

        Media banner = new Media();
        banner.setUrl("banner.png");
        banner.setType(MediaTypeEnum.BANNER);
        banner.setUser(new User(userUuid));
        mediaRepository.save(banner);
    }

}
