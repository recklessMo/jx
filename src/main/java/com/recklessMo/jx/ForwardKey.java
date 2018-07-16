package com.recklessMo.jx;

import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.JSValue;
import com.teamdev.jxbrowser.chromium.LoggerProvider;
import com.teamdev.jxbrowser.chromium.events.FinishLoadingEvent;
import com.teamdev.jxbrowser.chromium.events.LoadAdapter;
import com.teamdev.jxbrowser.chromium.swing.BrowserView;

import javax.swing.*;
import java.awt.*;
import java.util.logging.Level;

/**
 * This sample demonstrates how to create and forward keyboard events
 * containing characters, modifiers, and control keys to Chromium engine.
 */
public class ForwardKey {
    public static void main(String[] args) {
        LoggerProvider.setLevel(Level.OFF);

        Browser browser = new Browser();
        BrowserView view = new BrowserView(browser);

        JFrame frame = new JFrame();
        frame.getContentPane().add(view, BorderLayout.CENTER);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        browser.addLoadListener(new LoadAdapter() {
            @Override
            public void onFinishLoadingFrame(FinishLoadingEvent event) {
                if (event.isMainFrame()) {
                    Browser browser = event.getBrowser();
                    JSValue value = browser.executeJavaScriptAndReturnValue("window");
                    value.asObject().setProperty("Account", new Account());
                }
            }
        });

        browser.loadHTML("<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "    <script type=\"text/javascript\">\n" +
                "        function sendFormData() {\n" +
                "            var firstNameValue = myForm.elements['firstName'].value;\n" +
                "            var lastNameValue = myForm.elements['lastName'].value;\n" +
                "            // Invoke the 'save' method of the 'Account' Java object.\n" +
                "            var res = Account.save(firstNameValue, lastNameValue);\n" +
                "            alert(res);\n" +
                "        }\n" +
                "    </script>\n" +
                "</head>\n" +
                "<body>\n" +
                "<form name=\"myForm\">\n" +
                "    First name: <input type=\"text\" name=\"firstName\"/>\n" +
                "    <br/>\n" +
                "    Last name: <input type=\"text\" name=\"lastName\"/>\n" +
                "    <br/>\n" +
                "    <input type=\"button\" value=\"Save\" onclick=\"sendFormData();\"/>\n" +
                "</form>\n" +
                "</body>\n" +
                "</html>");
    }

    public static class Account {
        public String save(String firstName, String lastName) {
            System.out.println("firstName = " + firstName);
            System.out.println("lastName = " + lastName);
            return "firstName is " + firstName + ", lastName = " + lastName;
        }
    }
}