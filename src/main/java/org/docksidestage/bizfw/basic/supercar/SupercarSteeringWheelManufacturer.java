/*
 * Copyright 2019-2022 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.docksidestage.bizfw.basic.supercar;

import org.docksidestage.bizfw.basic.screw.ScrewSpec;
import org.docksidestage.bizfw.basic.screw.SpecialScrew;
import org.docksidestage.bizfw.basic.screw.SpecialScrewManufacturer;

/**
 * The manufacturer(製造業者) of supercar steering wheel(車のハンドル).
 * @author jflute
 */
public class SupercarSteeringWheelManufacturer {

    private final SupercarSteeringWheelComponentDB componentDB = createSupercarSteeringWheelComponentDB();

    protected SupercarSteeringWheelComponentDB createSupercarSteeringWheelComponentDB() {

        return new SupercarSteeringWheelComponentDB();
    }

    public SteeringWheel makeSteeringWheel(Integer steeringWheelId) {
        String specText = componentDB.findClincherSpecText(steeringWheelId);
        ScrewSpec screwSpec = new ScrewSpec(specText);

        SpecialScrewManufacturer screwManufacturer = createSpecialScrewManufacturer();
        try {
            SpecialScrew screw = screwManufacturer.makeSpecialScrew(screwSpec);
            return new SteeringWheel(screw);
        } catch (RuntimeException e) {
            String msg = "Failed to make steering wheel due to special screw issue for ID: " + steeringWheelId;
            throw new SupercarManufactureException(msg, e);
        }
    }

    protected SpecialScrewManufacturer createSpecialScrewManufacturer() {
        return new SpecialScrewManufacturer();
    }

    public static class SteeringWheel {

        private SpecialScrew screw;

        public SteeringWheel(SpecialScrew screw) {
            this.screw = screw;
        }

        public SpecialScrew getScrew() {
            return screw;
        }

        @Override
        public String toString() {
            return "SteeringWheel[screw=" + screw + "]";
        }
    }
}
