/*
 * SPDX-License-Identifier: MIT
 *
 * The MIT License (MIT)
 *
 * Copyright (c) 2015-2022 games647 and contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.github.games647.fastlogin.velocity.event;

import com.github.games647.fastlogin.core.StoredProfile;
import com.github.games647.fastlogin.core.shared.LoginSession;
import com.github.games647.fastlogin.core.shared.event.FastLoginAutoLoginEvent;
import com.velocitypowered.api.event.ResultedEvent;

import java.util.Objects;

public class VelocityFastLoginAutoLoginEvent
    implements FastLoginAutoLoginEvent, ResultedEvent<ResultedEvent.GenericResult> {

    private final LoginSession session;
    private final StoredProfile profile;
    private boolean cancelled;

    public VelocityFastLoginAutoLoginEvent(LoginSession session, StoredProfile profile) {
        this.session = session;
        this.profile = profile;
    }

    @Override
    public LoginSession getSession() {
        return session;
    }

    @Override
    public StoredProfile getProfile() {
        return profile;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }


    @Override
    public GenericResult getResult() {
        return cancelled ? GenericResult.denied() : GenericResult.allowed();
    }

    @Override
    public void setResult(GenericResult result) {
        cancelled = Objects.requireNonNull(result) != GenericResult.allowed();
    }
}
