//
//import com.googlecode.javacv.cpp.opencv_contrib.FaceRecognizer;
//import static com.googlecode.javacv.cpp.opencv_contrib.createFisherFaceRecognizer;
//import static com.googlecode.javacv.cpp.opencv_core.IPL_DEPTH_8U;
//import com.googlecode.javacv.cpp.opencv_core.IplImage;
//import com.googlecode.javacv.cpp.opencv_core.MatVector;
//import static com.googlecode.javacv.cpp.opencv_highgui.cvLoadImage;
//import static com.googlecode.javacv.cpp.opencv_imgproc.CV_BGR2GRAY;
//import static com.googlecode.javacv.cpp.opencv_imgproc.cvCvtColor;
//import java.io.File;
//import java.io.FilenameFilter;
//import java.io.IOException;
//import javax.xml.parsers.DocumentBuilder;
//import javax.xml.parsers.DocumentBuilderFactory;
//import javax.xml.parsers.ParserConfigurationException;
//import javax.xml.transform.Transformer;
//import javax.xml.transform.TransformerConfigurationException;
//import javax.xml.transform.TransformerException;
//import javax.xml.transform.TransformerFactory;
//import javax.xml.transform.dom.DOMSource;
//import javax.xml.transform.stream.StreamResult;
//import org.w3c.dom.Document;
//import org.w3c.dom.Element;
//import org.xml.sax.SAXException;
//
//public class OpenCVFaceRecognizer {
//
//    static void adauga(String id) {
//        try {
//            DocumentBuilderFactory dFact = DocumentBuilderFactory.newInstance();
//            DocumentBuilder build = dFact.newDocumentBuilder();
//            Document doc = build.newDocument();
//            Element root = doc.createElement("root");
//            doc.appendChild(root);
//            Element Info = doc.createElement("Info");
//            root.appendChild(Info);
//            Element recogn = doc.createElement("recog");
//            Info.appendChild(recogn);
//
//            Element idt = doc.createElement("ID");
//            idt.appendChild(doc.createTextNode(id));
//            recogn.appendChild(idt);
//
//            TransformerFactory tFact = TransformerFactory.newInstance();
//            Transformer trans = tFact.newTransformer();
//
//            StreamResult result = new StreamResult(new File("Face.xml"));
//            DOMSource source = new DOMSource(doc);
//            trans.transform(source, result);
//
//        } catch (ParserConfigurationException ex) {
//            // Logger.getLogger(XmlCreate.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (TransformerConfigurationException ex) {
//            // Logger.getLogger(XmlCreate.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (TransformerException ex) {
//            // Logger.getLogger(XmlCreate.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
//    static void adauga2(String id) {
//        try {
//            DocumentBuilderFactory dFact = DocumentBuilderFactory.newInstance();
//            DocumentBuilder build = dFact.newDocumentBuilder();
//            Document doc = build.parse(new File("Face.xml"));
//            Element dataTag = doc.getDocumentElement();
//            Element root = (Element) dataTag.getElementsByTagName("Info").item(0);
//            Element recogn = doc.createElement("recog");
//            root.appendChild(recogn);
//            Element idt = doc.createElement("ID");
//            idt.appendChild(doc.createTextNode(id));
//
//            recogn.appendChild(idt);
//
//            DOMSource source = new DOMSource(doc);
//
//            TransformerFactory transformerFactory = TransformerFactory.newInstance();
//            Transformer transformer = transformerFactory.newTransformer();
//            StreamResult result = new StreamResult("Face.xml");
//            transformer.transform(source, result);
//
//        } catch (ParserConfigurationException | SAXException | IOException ex) {
//            // Logger.getLogger(XmlCreate.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        // Logger.getLogger(XmlCreate.class.getName()).log(Level.SEVERE, null, ex);
//        // Logger.getLogger(XmlCreate.class.getName()).log(Level.SEVERE, null, ex);
//         catch (TransformerConfigurationException ex) {
//            // Logger.getLogger(XmlCreate.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (TransformerException ex) {
//            // Logger.getLogger(XmlCreate.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
//    public static void FaceRec(String trainDir, String imgToRec) {
//        String trainingDir = trainDir;
//        IplImage testImage = cvLoadImage(imgToRec);
//
//        File root = new File(trainingDir);
//
//        FilenameFilter pngFilter = new FilenameFilter() {
//            public boolean accept(File dir, String name) {
//                return name.toLowerCase().endsWith(".png");
//            }
//        };
//
//        File[] imageFiles = root.listFiles(pngFilter);
//
//        MatVector images = new MatVector(imageFiles.length);
//
//        int[] labels = new int[imageFiles.length];
//
//        int counter = 0;
//        int label;
//
//        IplImage img;
//        IplImage grayImg;
//
//        for (File image : imageFiles) {
//            img = cvLoadImage(image.getAbsolutePath());
//
//            label = Integer.parseInt(image.getName().split("\\-")[0]);
//
//            grayImg = IplImage.create(img.width(), img.height(), IPL_DEPTH_8U, 1);
//
//            cvCvtColor(img, grayImg, CV_BGR2GRAY);
//
//            images.put(counter, grayImg);
//
//            labels[counter] = label;
//
//            counter++;
//        }
//
//        IplImage greyTestImage = IplImage.create(testImage.width(), testImage.height(), IPL_DEPTH_8U, 1);
//
//        FaceRecognizer faceRecognizer = createFisherFaceRecognizer();
//        // FaceRecognizer faceRecognizer = createEigenFaceRecognizer();
//        // FaceRecognizer faceRecognizer = createLBPHFaceRecognizer()
//
//        faceRecognizer.train(images, labels);
//
//        cvCvtColor(testImage, greyTestImage, CV_BGR2GRAY);
//
//        int predictedLabel = faceRecognizer.predict(greyTestImage);
//
//        boolean ok = new File("Face.xml").exists();
//        if (ok == false) {
//            adauga(Integer.toString(predictedLabel));
//        } else {
//            adauga2(Integer.toString(predictedLabel));
//        }
//
//        System.out.println("Predicted label: " + predictedLabel);
//    }
//
//    public static void main(String[] args) {
//        FaceRec(args[0],args[1]);
//    }
//}
