package edu.kit.informatik.data;

import java.util.ArrayList;

/**
 * This class contains the network storage and various methods.
 *
 * @author Tilmann Rothe Santos
 * @version 1.0
 */
public class NetworkStorage {

    private ArrayList<Network> networkStorage;

    /**
     * constructor to initialize the network storage (in the data class)
     */
    public NetworkStorage() {
        networkStorage = new ArrayList<>();
    }

    /**
     * size-method
     *
     * @return int with size of the network storage list
     */
    public int size() {
        return networkStorage.size();
    }

    /**
     * get-method with index
     *
     * @param index int
     * @return network at index
     */
    public Network get(int index) {
        return networkStorage.get(index);
    }

    /**
     * get-method with name
     *
     * @param lookForNetwork String with the name
     * @return returns the network
     */
    public Network getNetwork(String lookForNetwork) {
        for (Network network : networkStorage) {
            if (network.getName().equals(lookForNetwork)) {
                return network;
            }
        }
        return null;
    }

    /**
     * add-method
     *
     * @param newNetwork the network which will be add to the list
     */
    public void addNetworkToStorage(Network newNetwork) {
        networkStorage.add(newNetwork);
    }

    /**
     * method to check if a network exist in the storage
     *
     * @param networkLookFor the network which needs to be checked
     * @return true: if the network exists, false: if not
     */
    public boolean doesNetworkExist(Network networkLookFor) {
        return networkLookFor != null;
    }

    /**
     * method to check if a network exists by the name
     *
     * @param networkName the name of the network
     * @return true: if the network exists, false: if not
     */
    public boolean doesNetworkExistByName(String networkName) {
        return getNetwork(networkName) != null;
    }

}
