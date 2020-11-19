//package org.imd.cqrs.sample1.cs.es.service;
//
//import lombok.RequiredArgsConstructor;
//import org.apache.tomcat.jni.Address;
//import org.imd.cqrs.sample1.cs.es.events.Event;
//import org.imd.cqrs.sample1.cs.es.events.post.PostCreatedEvent;
//import org.imd.cqrs.sample1.cs.es.events.postcomment.PostCommentAddedEvent;
//import org.imd.cqrs.sample1.cs.es.events.postcomment.PostCommentChangedEvent;
//import org.imd.cqrs.sample1.cs.es.events.postcomment.PostCommentDeletedEvent;
//import org.imd.cqrs.sample1.cs.es.store.EventStore;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//import java.util.UUID;
//
//@Component
//@RequiredArgsConstructor
//public class AggregateUtilityCopy {
//
//    private final EventStore eventStore;
//
//    public User readAggregateInfo(Long userId) {
//        User user = null;
//
//        List<Event> events = eventStore.getUserEvents(userId);
//        for (Event event : events) {
//            if (event instanceof PostCreatedEvent) {
//                PostCreatedEvent e = (PostCreatedEvent) event;
//                user = new User(UUID.randomUUID()
//                        .toString(), e.getFirstName(), e.getLastName());
//            }
//            if (event instanceof PostCommentAddedEvent) {
//                PostCommentAddedEvent e = (PostCommentAddedEvent) event;
//                Address address = new Address(e.getAddressId(), e.getCity(), e.getState(), e.getPostCode());
//                if (user != null)
//                    user.getAddresses()
//                            .add(address);
//            }
//            if (event instanceof PostCommentChangedEvent) {
//                PostCommentChangedEvent e = (PostCommentChangedEvent) event;
//                if (user != null) {
//                    user.getAddresses()
//                            .stream()
//                            .filter(a -> a.getAddressId().equals(e.getAddressId()))
//                            .findFirst()
//                            .ifPresent(a -> {
//                                a.setCity(e.getCity());
//                                a.setPostcode(e.getPostCode());
//                                a.setState(e.getState());
//                            });
//                }
//            }
//            if (event instanceof PostCommentDeletedEvent) {
//                PostCommentDeletedEvent e = (PostCommentDeletedEvent) event;
//                Address address = new Address(e.getAddressId());
//                if (user != null)
//                    user.getAddresses()
//                            .remove(address);
//            }
//        }
//
//        return user;
//    }
//
//}
