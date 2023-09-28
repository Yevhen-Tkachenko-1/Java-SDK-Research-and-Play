package interview.site.visitor;


import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Consumer;

/**
 * There is a site.
 * Site has 5 pages.
 * Multiple users can visit site's pages.
 * When user will visit all 5 pages send an event that user completed visiting site.
 * Everyone can subscribe to receive events about site visiting completion.
 * All 3 method can be invoked in multithreading environment, so they should be threadsafe.
 * Subscribers should be notified only once (at first pages passing of specific user)
 */
public class SiteVisitor {

    public static Set<Integer> ALL_PAGES = Set.of(1, 2, 3, 4, 5);

    private final ConcurrentHashMap<Long, Set<Integer>> visits = new ConcurrentHashMap<>();

    public void visitPage(long userId, int pageId){
        Set<Integer> visitedPages = visits.compute(userId, (user, visits)->{
            Set<Integer> result = visits == null ? new HashSet<>() : visits;
            result.add(pageId);
            return result;
        });
        if(ALL_PAGES.equals(visitedPages)){
            notify(userId);
        }
    }

    private final CopyOnWriteArraySet<Consumer<Long>> subscribers = new CopyOnWriteArraySet<>();
    private final CopyOnWriteArraySet<Long> passedUsers = new CopyOnWriteArraySet<>();

    public void subscribe(Consumer<Long> consumer){
        subscribers.add(consumer);
    }

    private void notify(Long userId){
        lock.lock();
        try{
            if(passedUsers.contains(userId)){
                return;
            }
            passedUsers.add(userId);
        }finally {
            lock.unlock();
        }
        subscribers.forEach(subscriber -> subscriber.accept(userId));
    }

    private final ReentrantLock lock = new ReentrantLock();
}
