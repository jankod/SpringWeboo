package hr.ja.weboo.ui.widgets;

import hr.ja.weboo.ui.PageContext;

import java.time.Duration;

/**
 *    @Override
 *     public Duration ttl() {
 *         return Duration.ofMinutes(10);
 *     }
 *
 *     @Override
 *     public String cacheKey(PageContext ctx) {
 *         return "footer";
 *     }
 *     public String render(Widget widget, PageContext ctx) {
 *
 *     if (widget instanceof CacheableWidget c) {
 *         return cache.get(
 *             c.cacheKey(ctx),
 *             c.ttl(),
 *             () -> renderInternal(widget, ctx)
 *         );
 *     }
 *
 *     return renderInternal(widget, ctx);
 * }
 */
public interface CacheableWidget {
    Duration ttl();
    String cacheKey(PageContext ctx);
}
