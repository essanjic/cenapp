package com.group6.cenapp.service;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.*;


@Service
public class ImageConverterAndDatabaseOperator {

    public byte[] convertImageToBytes(File imageFile) {
        BufferedImage bufferedImage = null;
        try {
            bufferedImage = ImageIO.read(imageFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (bufferedImage == null) {
            return null;
        }

        byte[] imageBytes = null;
        imageBytes = IOUtils.toByteArray(String.valueOf(bufferedImage));
        return imageBytes;
    }

    public void persistImageBytesInDatabase(byte[] imageBytes, String databaseUrl, String tableName) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DriverManager.getConnection(databaseUrl);
            preparedStatement = connection.prepareStatement("INSERT INTO " + tableName + " (image_data) VALUES (?)");
            preparedStatement.setBytes(1, imageBytes);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public byte[] readImageBytesFromDatabase(String databaseUrl, String tableName, int imageId) {
        Connection connection = null;
        ResultSet resultSet = null;
        byte[] imageBytes = null;

        try {
            connection = DriverManager.getConnection(databaseUrl);
            String query = "SELECT image_data FROM " + tableName + " WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, imageId);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                imageBytes = resultSet.getBytes("image_data");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return imageBytes;
    }

    public void updateImageBytesInDatabase(byte[] imageBytes, String databaseUrl, String tableName, int imageId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DriverManager.getConnection(databaseUrl);
            preparedStatement = connection.prepareStatement("UPDATE " + tableName + " SET image_data = ? WHERE id = ?");
            preparedStatement.setBytes(1, imageBytes);
            preparedStatement.setInt(2, imageId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void deleteImageBytesFromDatabase(String databaseUrl, String tableName, int imageId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DriverManager.getConnection(databaseUrl);
            preparedStatement = connection.prepareStatement("DELETE FROM " + tableName + " WHERE id = ?");
            preparedStatement.setInt(1, imageId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
