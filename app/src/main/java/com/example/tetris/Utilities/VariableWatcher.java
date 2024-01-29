package com.example.tetris.Utilities;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class VariableWatcher {
    private Object monitoredObject;
    private String variableName;
    private PropertyChangeSupport changeSupport;

    public VariableWatcher(Object object, String variable) {
        monitoredObject = object;
        variableName = variable;
        startWatching();
    }

    private void startWatching() {
        PropertyChangeListener listener = event -> {
            if (event.getPropertyName().equals(variableName)) {
                Object newValue = event.getNewValue();
                System.out.println("New value detected: " + newValue);
            }
        };
        ((java.beans.PropertyChangeSupport) monitoredObject)
                .addPropertyChangeListener(listener);
    }

    public void addChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }
}