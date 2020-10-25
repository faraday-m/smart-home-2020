package ru.sbt.mipt.oop.elements;

import ru.sbt.mipt.oop.actions.Action;
import ru.sbt.mipt.oop.events.Event;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static ru.sbt.mipt.oop.events.typedefs.EventType.LIGHTS_OFF;

public class SmartHome implements HomeComponent, HomeComponentComposite {
    private Collection<Room> rooms;

    public SmartHome() {
        rooms = new ArrayList<>();
    }

    public SmartHome(Collection<Room> rooms) {
        this.rooms = rooms;
    }


    @Override
    public void addHomeComponent(ElementType type, HomeComponent component) {
        if (type == HomeElementType.ROOM) {
            rooms.add((Room) component);
        }
    }

    @Override
    public Collection<? extends HomeComponent> getComponents(Predicate<? super HomeComponent> condition) {
        List<HomeComponent> resultList = rooms.stream().filter(condition).collect(Collectors.toList());
        List<HomeComponent> componentList = rooms.stream().map((Room r) -> r.getComponents(condition)).flatMap(Collection::stream).collect(Collectors.toList());
        resultList.addAll(componentList);
        return resultList;
    }

    @Override
    public HomeComponent getComponent(Predicate<? super HomeComponent> condition) {
        HomeComponent room = rooms.stream().filter(condition).findFirst().orElse(null);
        if (room != null) {
            return room;
        }
        return getComponents(condition).stream().findFirst().orElse(null);
    }

    public int getElementCount(ElementType type) {
        if (type == HomeElementType.ROOM) {
            return rooms.size();
        } else {
            return rooms.stream().mapToInt((Room r) -> r.getElementCount(type)).reduce(Integer::sum).orElse(0);
        }
    }

    @Override
    public ElementType getType() {
        return HomeElementType.SMART_HOME;
    }

    @Override
    public ComponentId getId() {
        return new StringId("HOME");
    }

    public Event apply(Event event, Action action) {
        Event newEvent = rooms.stream()
                .map((Room r) -> r.apply(event, action))
                .filter((Event e) -> (e.getType() == LIGHTS_OFF)).collect(Collectors.toList()).stream().findFirst().orElse(null);
        if (newEvent != null) {
            return newEvent;
        }
        return event;
    }
}
