package com.guandan.core.action;

import com.guandan.core.annotation.Protocol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@Component
public class DispatcherAction {

    @Autowired
    private ApplicationContext context;
    private Map<Integer, BaseAction> actionCache = new HashMap<>();


    public Optional<BaseAction> createAction(int protocol) {
        if (actionCache != null) {
            return Optional.of(actionCache.get(protocol));
        }
        return Optional.empty();
    }

    @PostConstruct
    public void init() {
        for (String bean : context.getBeanNamesForAnnotation(Protocol.class)) {
            Object o = context.getBean(bean);
            Protocol protocol = o.getClass().getAnnotation(Protocol.class);

            if(o.getClass().getSuperclass().equals(BaseAction.class)) {
                actionCache.put(protocol.value(), (BaseAction) o);
            }
        }
    }
}
