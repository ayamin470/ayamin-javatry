/*
 * Copyright 2019-2022 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.docksidestage.bizfw.basic.supercar;

/**
 * Supercar client related exception.
 * This exception indicates an issue originating from the client's request or a high-level process failure.
 * <p>
 * This is a runtime exception because it is expected to be handled at the application's boundary
 * (e.g., web layer, service layer facade).
 * </p>
 * @author jflute (modified by your AI for this challenge)
 */
public class SupercarClientException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public SupercarClientException(String msg) {
        super(msg);
    }

    public SupercarClientException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
