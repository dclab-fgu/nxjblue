
package edu.fgu.dclab.handheld;

import edu.fgu.dclab.bluetooth.NodeClient;

public abstract class Handheld {
    protected NodeClient node;

    public Handheld() {
        node = new NodeClient();
    } // Handheld()
} // Handheld

// Handheld.java
