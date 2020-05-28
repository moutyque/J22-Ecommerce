package com.ecommerce.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletOutputStream;

public final class Helper {

	public static void witteFile(File from, ServletOutputStream to)
			throws IOException {

		try (BufferedInputStream in = new BufferedInputStream(
				new FileInputStream(from));
				BufferedOutputStream out = new BufferedOutputStream(to)) {
			witteFile(in, out);

		} catch (IOException e) {
			throw new IOException("Erreur lors de l'écriture du fichier.",
					e.getCause());
		}

	}

	public static void witteFile(InputStream from, File to) throws IOException {

		try (BufferedInputStream in = new BufferedInputStream(from);
				BufferedOutputStream out = new BufferedOutputStream(
						new FileOutputStream(to))) {
			witteFile(in, out);

		} catch (IOException e) {
			throw new IOException("Erreur lors de l'écriture du fichier.",
					e.getCause());
		}

	}

	public static void witteFile(InputStream from, BufferedOutputStream to)
			throws IOException {
		try (BufferedInputStream in = new BufferedInputStream(from)) {

			witteFile(in, to);
		} catch (IOException e) {
			throw new IOException("Erreur lors de l'écriture du fichier",
					e.getCause());
		}
	}

	public static void witteFile(BufferedInputStream from,
			BufferedOutputStream to) throws IOException {
		try {

			byte[] tampon = new byte[1024];
			int longueur = 0;
			while ((longueur = from.read(tampon)) > 0) {
				to.write(tampon, 0, longueur);
			}
		} catch (IOException e) {
			throw new IOException("Erreur lors de l'écriture du fichier.",
					e.getCause());
		}
	}

}
