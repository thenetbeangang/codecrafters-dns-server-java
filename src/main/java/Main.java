import java.io.IOException;

import java.net.DatagramPacket;

import java.net.DatagramSocket;

import java.nio.ByteBuffer;

import java.nio.ByteOrder;

import java.util.BitSet;

public class Main {

  public static void main(String[] args) {

    // You can use print statements as follows for debugging, they'll be visible

    // when running tests.

    System.out.println("Logs from your program will appear here!");

    // Uncomment this block to pass the first stage

    // 1st stage

    try (DatagramSocket serverSocket = new DatagramSocket(2053)) {

      while (true) {

        final byte[] buf = new byte[512];

        final DatagramPacket packet = new DatagramPacket(buf, buf.length);

        serverSocket.receive(packet);

        System.out.println("Received data");

        // final byte[] bufResponse = new byte[512];

        short ID = (short) 1234;

        final var bitset = new BitSet(8);

        bitset.flip(7);

        final var bufResponse = ByteBuffer.allocate(512)

            .order(ByteOrder.BIG_ENDIAN)

            .putShort(ID)

            .put(bitset.toByteArray())

            .put((byte) 0)

            .putShort((short) 0)

            .putShort((short) 0)

            .putShort((short) 0)

            .putShort((short) 0)

            .array();

        final DatagramPacket packetResponse = new DatagramPacket(

            bufResponse, bufResponse.length, packet.getSocketAddress());

        serverSocket.send(packetResponse);

      }

    } catch (IOException e) {

      System.out.println("IOException: " + e.getMessage());

    }

  }

}