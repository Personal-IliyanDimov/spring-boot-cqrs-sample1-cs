package org.imd.cqrs.sample1.model.domain.aggregate;

import org.imd.cqrs.sample1.es.event.Event;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractAggregate {

    // inited
    private long sourceVersion;
    private long targetVersion;
    private LocalDateTime lastUpdated;

    // changes since inited;
    private List<Event> changes;

    protected void initState(long sourceVersion, List<Event> eventList) {
        changes = new ArrayList<>();

        this.sourceVersion = sourceVersion;
        this.targetVersion = sourceVersion;

        for (Event event: eventList) {
            apply(event, false, false);
        }
    }

    public List<Event> getChanges() {
        return changes;
    }

    public long getSourceVersion() {
        return sourceVersion;
    }

    public long getTargetVersion() {
        return targetVersion;
    }

    protected void apply(Event event) {
        apply(event, true, true);
    }

    private void apply(Event event, boolean appendToChanges, boolean incrementTargetVersion) {
        doApply(event);

        if (appendToChanges) {
            changes.add(event);
        }

        lastUpdated = event.getWhenOccurs();

        if (incrementTargetVersion) {
            targetVersion++;
        }
    }

    protected abstract void doApply(Event event);

}
