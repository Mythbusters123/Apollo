/*⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼
  Copyright (C) 2020-2021 developed by Icovid and Apollo Development Team

  This program is free software: you can redistribute it and/or modify
  it under the terms of the GNU Affero General Public License as published
  by the Free Software Foundation, either version 3 of the License, or
  (at your option) any later version.
  This program is distributed in the hope that it will be useful,
  but WITHOUT ANY WARRANTY; without even the implied warranty of
  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  GNU Affero General Public License for more details.
  You should have received a copy of the GNU Affero General Public License
  along with this program.  If not, see https://www.gnu.org/licenses/.

  Contact: Icovid#3888 @ https://discord.com
 ⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼*/

package net.apolloclient.event.bus;

import net.apolloclient.event.Priority;
import net.apolloclient.module.bus.EventHandler;

import java.lang.reflect.Method;

/**
 * Container object used to hold needed information gathered by the @{@link EventHandler}
 * annotation by the {@link EventBus} and to invoke methods using a single event parameter.
 *
 * <p>To invoke a {@link HandlerEventContainer} use an instance of the objects {@link #invoke(Object...)}
 * method with a single event parameter.</p>
 *
 * @author Icovid | Icovid#3888
 * @see EventHandler annotation this contains
 * @since 1.2.0-BETA
 */
public class HandlerEventContainer {

    /** the instance of the object to invoke methods on */
    public final Object instance;
    /** the method to invoke with event parameter */
    public final Method method;
    /** the priority of event method over other event methods */
    private final Priority priority;

    /**
     * Creates a new {@link HandlerEventContainer} instance with the given information.
     *
     * @param instance the instance of the object to invoke methods on
     * @param method   the method to invoke with event parameter
     * @param priority the priority of event method over other event methods
     */
    public HandlerEventContainer(Object instance, Method method, Priority priority) {
        this.instance = instance;
        this.method   = method;
        this.priority = priority;
    }

    /**
     * Invoke method using instance.
     *
     * @param args the arguments for invoking method.
     */
    public void invoke(Object... args) {
        try {
            this.method.invoke(instance, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @return the priority of method
     */
    public Priority getPriority() { return priority; }
}
