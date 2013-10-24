package com.yammer.chesster.service.store;

import com.google.common.base.Optional;
import com.google.common.collect.Maps;
import com.yammer.chesster.service.model.Game;
import com.yammer.chesster.service.model.GameProperty;
import com.yammer.dropwizard.hibernate.AbstractDAO;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

public class GamePropertyStore extends AbstractDAO<GameProperty> {

    public GamePropertyStore(SessionFactory provider) {
        super(provider);
    }

    public List<GameProperty> getGameProperties(String propertyKey, String propertyValue) {
        Criteria criteria = currentSession().createCriteria(GameProperty.class)
                .setReadOnly(true)
                .add(Restrictions.eq("propertyKey", propertyKey))
                .add(Restrictions.eq("propertyValue", propertyValue));
        return list(criteria);
    }
}
