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

package net.apolloclient.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IImageBuffer;
import net.minecraft.client.renderer.ImageBufferDownload;
import net.minecraft.client.renderer.ThreadDownloadImageData;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.io.FilenameUtils;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.stream.Collectors;

/**
 * Helper methods used to download data from the https://static.apolloclient.net web server
 * asynchronously to avoid game freezing on startup
 *
 * @author Icovid | Icovid#3888
 * @since 1.2.0-BETA
 */
public class DataDownloader {

    /**
     * Asynchronous data downloader with callback
     *
     * @param url of file.
     */
    public static void downloadStringAsync(String url, DataCallback dataCallback) {
        Thread thread = new Thread(url + ":download") {

            /**
             * If this thread was constructed using a separate
             * <code>Runnable</code> run object, then that
             * <code>Runnable</code> object's <code>run</code> method is called;
             * otherwise, this method does nothing and returns.
             * <p>
             * Subclasses of <code>Thread</code> should override this method.
             *
             */
            @Override
            public void run() {
                try {
                    URLConnection urlConnection = new URL(url).openConnection();
                    urlConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 5.1; rv:19.0) Gecko/20100101 Firefox/19.0");
                    urlConnection.connect();
                    BufferedReader serverResponse = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    String response = serverResponse.lines().collect(Collectors.joining());
                    serverResponse.close();
                    dataCallback.stringCallback(response);
                }
                catch (Exception exception) {
                    dataCallback.stringCallback(null);
                }
            }
        };
        thread.start();
    }

    /**
     *
     *
     * @param url url of file
     * @param dataCallback callback for downloaded resource
     */
    public static void downloadTextureAsync(String url, DataCallback dataCallback) {

    }

    /**
     * Downloads data from url
     *
     * @param url url to download
     * @return data downloaded
     */
    public static String downloadString(String url) {
        try {
            URLConnection urlConnection = new URL(url).openConnection();
            urlConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 5.1; rv:19.0) Gecko/20100101 Firefox/19.0");
            urlConnection.connect();
            BufferedReader serverResponse = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String response = serverResponse.lines().collect(Collectors.joining());
            serverResponse.close();
            return response;
        } catch (Exception exception) {
            throw new RuntimeException("Could not download data from :" + url);
        }
    }

    /**
     * Callback of downloaded data when using async.
     *
     *  @author Icovid | Icovid#3888
     *  @since b0.2
     */
    public static interface DataCallback {

        /**
         * Override to get data retried in {@code downloadStringAsync }
         *
         * @param data string downloaded
         */
        default void stringCallback(String data) { };

        /**
         * Override to get data retried in {@code downloadStringAsync }
         *
         * @param data resource downloaded
         */
        default void resourceCallback(ResourceLocation data) { };
    }
}
