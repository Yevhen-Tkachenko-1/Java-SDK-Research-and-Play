package interview.visit.counter;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class VisitCounter {

    public static void main(String[] args) {

        Map<Long, Long> expected = Map.of(1L, 10L, 2L, 20L, 3L, 30L);
        Map<Long, Long> actual = new VisitCounter().count(
                Map.of("1", new UserStats(1), "2", new UserStats(1), "3", new UserStats(15)),
                Map.of("1", new UserStats(9), "2", new UserStats(19), "3", new UserStats(15)));
        System.out.println(expected.equals(actual));
    }

    Map<Long, Long> count(Map<String, UserStats>... visits) {
        if (visits == null) {
            return Collections.emptyMap();
        }
        if (visits.length == 0) {
            return Collections.emptyMap();
        }
        List<Map<String, UserStats>> nonNullVisits = Arrays.stream(visits).filter(statsMap -> statsMap != null).collect(Collectors.toList());
        if (nonNullVisits.isEmpty()) {
            return Collections.emptyMap();
        }
        Map<Long, Long> allVisits = new HashMap<>();
        nonNullVisits.forEach(microserviceVisits -> populateMicroserviceVisits(microserviceVisits, allVisits));
        return allVisits;
    }

    void populateMicroserviceVisits(Map<String, UserStats> microserviceVisits, Map<Long, Long> allVisits) {
        microserviceVisits.forEach((userId, stats) -> putVisit(userId, stats, allVisits));
    }

    void putVisit(String anyUserId, UserStats userStats, Map<Long, Long> visits) {
        if (userStats == null) {
            return;
        }
        if (!userStats.getVisitCount().isPresent()) {
            return;
        }
        try {
            Long userId = Long.parseLong(anyUserId);
            Long count = userStats.getVisitCount().get();
            if (visits.containsKey(userId)) {
                count = visits.get(userId) + count;
            }
            visits.put(userId, count);
        } catch (NumberFormatException e) {
        }
    }

    static class UserStats{

        private final Long count;

        UserStats(Integer count) {
            this.count = Long.valueOf(count);
        }

        Optional<Long> getVisitCount(){
            return Optional.of(count);
        }
    }
}
