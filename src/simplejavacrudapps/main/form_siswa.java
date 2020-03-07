/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplejavacrudapps.main;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import java.util.Date;

/**
 *
 * @author Farras Jibran
 */
public class form_siswa extends javax.swing.JFrame {
    
    JPanel jpanel;
    DefaultTableModel model = new DefaultTableModel();
    
//    DON'T MODIFIED!!!!!!!!!!
    /**
     * Creates new form form_siswa
     */
    public form_siswa() {
        initComponents();
        
        // Koneksi ke database
        Koneksi konn = new Koneksi();
        konn.GetConnect();
        
         //  Setting agar nama table masuk kedalam table
                tableSiswa.setModel(model);
                model.addColumn("NIPD");
                model.addColumn("NISN");
                model.addColumn("Nama Siswa");
                model.addColumn("Umur");
                model.addColumn("Alamat");
                model.addColumn("Kelamin");
                model.addColumn("Agama");
                model.addColumn("Tempat Lahir");
                model.addColumn("Tanggal Lahir");
                model.addColumn("Nomor Handphone");
                model.addColumn("Email");
                model.addColumn("Jurusan");
                
                 // tampil data
                tampilData();  
        
    }
     
    // Fuctioning
    
            // Tampil data
            private
            void tampilData()
            {
                System.out.println("Tampil data");
                int row = tableSiswa.getRowCount();

                //  Perulangan menghapus data setelah di clear
                for (int a = 0; a < row; a++)
                    {
                        model.removeRow(0);
                    }

                //  Query mysql
                String query = "SELECT * FROM siswa";

                try
                    {
                        Connection conn = Koneksi.GetConnect(); // Memanggil koneksi
                        Statement st = conn.createStatement(); // Membuat statement untuk nantinya di eksekusi result
                        ResultSet rs = st.executeQuery(query); // Untuk mengeksekusi query mysql

                        //  While lopping data
                        while (rs
                                .next()) // rs adalah variable dari Resultset, apabila data ditemukan. maka looping akan terus berjalan hingga tidak ada.
                            {
                                String nipd  = rs.getString("nipd");
                                String nisn = rs.getString("nisn");
                                String namasiswa = rs.getString("namasiswa");
                                String umur = rs.getString("umur");
                                String alamat = rs.getString("alamat");
                                String kelamin = rs.getString("kelamin");
                                String agama = rs.getString("agama");
                                String tempatlhr = rs.getString("tempat_lahir");
                                String tgl_lhr = rs.getString("tgl_lahir");
                                String nomor_hp = rs.getString("nomor_hp");
                                String email = rs.getString("email");
                                String jurusan = rs.getString("jurusan");

                                // Array untuk menampung semua data
                                String[] datasiswa = {nipd, nisn, namasiswa, umur, alamat, kelamin, agama, tempatlhr, tgl_lhr, nomor_hp, email, jurusan};

                                // Menambahkan baris sesuai data yang telah di tampung
                                model.addRow(datasiswa);
                            }
                    }
                catch (Exception e)
                    {
                        System.out.println(e);
                    }
            }
        
            // Tambah data 
            private
            void tambahData()
            {
                // Menampung data sementara yang telah di input
                String nisn = textnisn.getText();
                String nama = textnama.getText();
                String umur = textumur.getText();
                String alamat = textalamat.getText();
                
               // Menentukan kelamin yang di pilih
               String kelamin = "";
               if (laki.isSelected())
                    {
                      kelamin = "Laki-laki";  
                    }
               else
                    {
                    kelamin = "Perempuan";
                    }

                 // Menentukan kelas dipilih dengan index
                String agama = "";
                switch (agamabox.getSelectedIndex())
                    {
                        case 0:
                            agama = "Islam";
                            break;

                        case 1:
                            agama = "Kristen";
                            break;
                            
                        case 2: 
                            agama = "Budha";
                            break;
                            
                        case 3:
                            agama = "Hindu";
                            break;
                            
                        case 4:
                            agama = "Konghucu";
                            break;
                            
                        case 5:
                            agama = "Atheis";
                            break;
                    }
                
                String tempatlahir = texttempat.getText();
                SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
                String datechoose = dateformat.format(tgllahir.getDate());
                String nomorhp = textnomorhp.getText();
                String email = textemail.getText();
                
                // Menentukan jurusan yang dipilih
                String jurusan = "";
                switch (jurusanbox.getSelectedIndex())
                    {
                        case 0:
                            jurusan = "Rekayasa Perangkat Lunak";
                            break;
                            
                        case 1:
                            jurusan = "Multimedia";
                            break;
                            
                        case 2:
                            jurusan = "Brodcasting";
                            break;
                            
                        case 3:
                            jurusan = "Teknik Komputer Jaringan";
                            break;
                            
                        case 4:
                            jurusan = "Teknik Elektronika Industri";
                            break;                          
                    }
                
//                // Panggil koneksi
                Connection conn = Koneksi.GetConnect();

                // Query untuk menambahkan data
                String query = "INSERT INTO siswa(nipd, nisn, namasiswa, umur, alamat, kelamin, agama, tempat_lahir, tgl_lahir, nomor_hp, email, jurusan)" +
                               "VALUES(NULL, '" + nisn + "',  '" + nama + "', '" + umur + "', '" + alamat + "', '" + kelamin + "', '" + agama + "', '" + tempatlahir + "', '" + datechoose + "', '" + nomorhp + "', '" + email + "', '" + jurusan + "');";
//
//                // Menyiapkan alamat untuk dieksekusi
                try
                    {
                        PreparedStatement ps = conn.prepareStatement(query);
                        ps.executeUpdate(query);
                        JOptionPane.showMessageDialog(null, "Data Telah Berhasil Ditambahkan");
                        System.out.println("Data telah ditambahkan");
                    }
                catch (Exception e)
                    {
                        System.out.println(e);
                        JOptionPane.showMessageDialog(null, "Data Gagal Ditambahkan");
                        System.out.println("Data gagal ditambahkan");
                    }
                finally
                    {
                        tampilData();
                        clearData();
                    }
            }
        
            //  Clear data 
            private
            void clearData()
                {
                    textnisn.setText(null);
                    textnama.setText(null);
                    textumur.setText(null);
                    textalamat.setText(null);
                    buttonGroup1.clearSelection();
                    agamabox.setSelectedIndex(0);
                    texttempat.setText(null);
                    tgllahir.setDate(null);
                    textnomorhp.setText(null);
                    textemail.setText(null);
                    jurusanbox.setSelectedIndex(0);

                }
            
            // Table get data
            private
            void tableGet()
            {
                 int i = tableSiswa.getSelectedRow();

                                // Mengambil data
                                String nipd = model.getValueAt(i, 0).toString();
                                String nisn = model.getValueAt(i, 1).toString();
                                String nama = model.getValueAt(i, 2).toString();
                                String umur = model.getValueAt(i, 3).toString();
                                String alamat = model.getValueAt(i, 4).toString();
                                String kelamin = model.getValueAt(i, 5).toString();
                                String agama = model.getValueAt(i, 6).toString();
                                String tempatlahir = model.getValueAt(i, 7).toString();
                                String nomor_hp = model.getValueAt(i, 9).toString();
                                String email = model.getValueAt(i, 10).toString();
                                String jurusan = model.getValueAt(i, 11).toString();   
                                
                                // Setting agama
                                if (agama.equals("Islam"))
                                    {
                                        agamabox.setSelectedIndex(0);
                                    }
                                else if (agama.equals("Kristen"))
                                    {
                                        agamabox.setSelectedIndex(1);
                                    }
                                else if (agama.equals("Budha"))
                                    {
                                        agamabox.setSelectedIndex(2);
                                    }
                                else if (agama.equals("Hindu"))
                                    {
                                        agamabox.setSelectedIndex(3);
                                    }
                                else if (agama.equals("Konghucu"))
                                    {
                                        agamabox.setSelectedIndex(4);
                                    }
                                else
                                    {
                                        agamabox.setSelectedIndex(5);
                                    }
                                
                                 // Setting laki agar sesuai data yang berada di table
                                if (kelamin.equals("Laki-laki"))
                                    {
                                        laki.setSelected(true);
                                    }
                                else
                                    {
                                        perempuan.setSelected(true);
                                    }

                                // Setting nilai yang telah ditampung kedalam text field
                                textnisn.setText(nisn);
                                textnama.setText(nama);
                                textumur.setText(umur);
                                textalamat.setText(alamat);
                                texttempat.setText(tempatlahir);
                                
                                // Get date
                                Date myDate = null;
                                    try 
                                    {
                                        myDate = (Date) new SimpleDateFormat("yyyy-MM-dd").parse((String) model.getValueAt(i, 8).toString());
                                        tgllahir.setDate(myDate);
                                    }
                                    catch (ParseException ex) 
                                    {
                                        Logger.getLogger(form_siswa.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                textnomorhp.setText(nomor_hp);
                                textemail.setText(email);
                                
                                // Setting agar jurusan dapat terpilih
                                if (jurusan.equals("Rekayasa Perangkat Lunak"))
                                    {
                                        jurusanbox.setSelectedIndex(0);
                                    }
                                else if (jurusan.equals("Multimedia"))
                                    {
                                        jurusanbox.setSelectedIndex(1);
                                    }
                                else if (jurusan.equals("Broadcasting"))
                                    {
                                        jurusanbox.setSelectedIndex(2);
                                    }
                                else if (jurusan.equals("Teknik Komputer Jaringan"))
                                    {
                                        jurusanbox.setSelectedIndex(3);
                                    }
                                else
                                    {
                                        jurusanbox.setSelectedIndex(4);
                                    } 
            }
            
            // Edit data
            private
            void editData()
            {
                int i = tableSiswa.getSelectedRow();

                                String nipd = model.getValueAt(i, 0).toString();
                                String nisn = textnisn.getText();
                                String nama = textnama.getText();
                                String umur = textumur.getText();
                                String alamat = textalamat.getText();
                                
                                // Get data kelamin
                                String kelamin = "";
                                if (buttonGroup1.equals("Laki-laki"))
                                    {
                                        kelamin = "Laki-laki";
                                    }
                                else
                                    {
                                        kelamin = "Perempuan";
                                    }
                                
                                // Get data agama
                                String agama = "";
                                switch (agamabox.getSelectedIndex()) 
                                {
                                    case 0:
                                        agama = "Islam";
                                        break;
                                        
                                    case 1:
                                        agama = "Kristen";
                                        break;
                                        
                                    case 2:
                                        agama = "Budha";
                                        break;
                                        
                                    case 3:
                                        agama = "Hindu";
                                        break;
                                        
                                    case 4:
                                        agama = "Konghucu";
                                        break;
                                        
                                    case 5:
                                        agama = "Atheis";
                                        break;
                                }
                                
                                String tempatlahir = texttempat.getText();
                                SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
                                String tgllahir = dateformat.format(this.tgllahir.getDate());
                                String nomorhp = textnomorhp.getText();
                                String email = textemail.getText();
                                String jurusan = "";
                                switch (jurusanbox.getSelectedIndex()) 
                                {
                                    case 0:
                                        jurusan = "Rekayasa Perangkat Lunak";
                                        break;
                                        
                                    case 1:
                                        jurusan = "Multimedia";
                                        break;
                                        
                                    case 2:
                                        jurusan = "Broadcasting";
                                        break;
                                        
                                    case 3:
                                        jurusan = "Teknik Komputer Jaringan";
                                        break;
                                        
                                    case 4:
                                        jurusan = "Teknik Elektronika Industri";
                                        break;                                      
                                }
                                

                                Connection conn = Koneksi.GetConnect();

                                String query = "UPDATE `siswa` SET `nisn` = '"+nisn+"', `namasiswa` = '"+nama+"', `umur` = '"+umur+"', `alamat` = '"+alamat+"', `kelamin` = '"+kelamin+"', "
                                        + "`agama` = '"+agama+"', `tempat_lahir` = '"+tempatlahir+"', `tgl_lahir` = '"+tgllahir+"', `nomor_hp` = '"+nomorhp+"', "
                                        + "`email` = '"+email+"', `jurusan` = '"+jurusan+"' WHERE `siswa`.`nipd` = '"+nipd+"';";

                                // Menyiapkan alamat untuk dieksekusi
                                try
                                    {
                                        PreparedStatement ps = conn.prepareStatement(query);
                                        ps.executeUpdate(query);
                                        JOptionPane.showMessageDialog(null, "Data Telah Berhasil Di Edit");
                                        System.out.println("Data telah di edit");
                                    }
                                catch (Exception a)
                                    {
                                        System.out.println(a);
                                        JOptionPane.showMessageDialog(null, "Data Gagal Di Edit");
                                        System.out.println("Data gagal di edit");
                                    }
                                finally
                                    {
                                        tampilData();
                                        clearData();
                                    }
            }
            
            // Hapus data
            private
            void hapusData()
            {
            int i = tableSiswa.getSelectedRow();

            String nipd = model.getValueAt(i, 0).toString();

            Connection conn = Koneksi.GetConnect();

            String query = "DELETE FROM `siswa` WHERE `siswa`.`nipd` = " + nipd + ";";

            // Menyiapkan alamat untuk dieksekusi
            try
            {
                PreparedStatement ps = conn.prepareStatement(query);
                ps.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Data Telah Berhasil Di Hapus");
                System.out.println("Data berhasil dihapus");
            } 
            catch (Exception a)
            {
                System.out.println(a);
                JOptionPane.showMessageDialog(null, "Data Gagal Di Hapus");
                System.out.println("Data gagal dihapus");
            } 
            finally
            {
            tampilData();
            clearData();
            }
            }
        
        

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        dateChooserPanelBeanInfo1 = new com.toedter.calendar.demo.DateChooserPanelBeanInfo();
        Header = new javax.swing.JLabel();
        Nama = new javax.swing.JLabel();
        Umur = new javax.swing.JLabel();
        textalamat = new javax.swing.JTextArea();
        textnama = new javax.swing.JTextArea();
        Umur1 = new javax.swing.JLabel();
        textumur = new javax.swing.JTextArea();
        ButtonTambah = new javax.swing.JButton();
        ButtonEdit = new javax.swing.JButton();
        ButtonClear = new javax.swing.JButton();
        ButtonHapus = new javax.swing.JButton();
        laki = new javax.swing.JRadioButton();
        Kelamin = new javax.swing.JLabel();
        perempuan = new javax.swing.JRadioButton();
        Nomorhp = new javax.swing.JLabel();
        textnomorhp = new javax.swing.JTextArea();
        Email = new javax.swing.JLabel();
        textemail = new javax.swing.JTextArea();
        Tgllahir = new javax.swing.JLabel();
        Nisn = new javax.swing.JLabel();
        textnisn = new javax.swing.JTextArea();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableSiswa = new javax.swing.JTable();
        jurusanbox = new javax.swing.JComboBox<>();
        Jurusan = new javax.swing.JLabel();
        tgllahir = new com.toedter.calendar.JDateChooser();
        texttempat = new javax.swing.JTextArea();
        agama = new javax.swing.JLabel();
        agamabox = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Header.setFont(new java.awt.Font("System Font", 1, 36)); // NOI18N
        Header.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Header.setText("DATA SISWA");

        Nama.setFont(new java.awt.Font("System Font", 1, 18)); // NOI18N
        Nama.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Nama.setText("Nama Siswa");
        Nama.setAutoscrolls(true);

        Umur.setFont(new java.awt.Font("System Font", 1, 18)); // NOI18N
        Umur.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Umur.setText("Umur");
        Umur.setAutoscrolls(true);

        textalamat.setColumns(20);
        textalamat.setFont(new java.awt.Font("System Font", 0, 14)); // NOI18N
        textalamat.setRows(5);
        textalamat.setMargin(new java.awt.Insets(5, 5, 5, 5));

        textnama.setColumns(20);
        textnama.setFont(new java.awt.Font("System Font", 0, 14)); // NOI18N
        textnama.setRows(5);
        textnama.setMargin(new java.awt.Insets(5, 5, 5, 5));

        Umur1.setFont(new java.awt.Font("System Font", 1, 18)); // NOI18N
        Umur1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Umur1.setText("Alamat");
        Umur1.setAutoscrolls(true);

        textumur.setColumns(20);
        textumur.setFont(new java.awt.Font("System Font", 0, 14)); // NOI18N
        textumur.setRows(5);
        textumur.setMargin(new java.awt.Insets(5, 5, 5, 5));

        ButtonTambah.setFont(new java.awt.Font("System Font", 1, 18)); // NOI18N
        ButtonTambah.setText("Tambah");
        ButtonTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonTambahActionPerformed(evt);
            }
        });

        ButtonEdit.setFont(new java.awt.Font("System Font", 1, 18)); // NOI18N
        ButtonEdit.setText("Edit");
        ButtonEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonEditActionPerformed(evt);
            }
        });

        ButtonClear.setFont(new java.awt.Font("System Font", 1, 18)); // NOI18N
        ButtonClear.setText("Clear");
        ButtonClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonClearActionPerformed(evt);
            }
        });

        ButtonHapus.setFont(new java.awt.Font("System Font", 1, 18)); // NOI18N
        ButtonHapus.setText("Hapus");
        ButtonHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonHapusActionPerformed(evt);
            }
        });

        buttonGroup1.add(laki);
        laki.setFont(new java.awt.Font("System Font", 1, 18)); // NOI18N
        laki.setText("Laki - Laki");

        Kelamin.setFont(new java.awt.Font("System Font", 1, 18)); // NOI18N
        Kelamin.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Kelamin.setText("Kelamin");
        Kelamin.setAutoscrolls(true);

        buttonGroup1.add(perempuan);
        perempuan.setFont(new java.awt.Font("System Font", 1, 18)); // NOI18N
        perempuan.setText("Perempuan");
        perempuan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                perempuanActionPerformed(evt);
            }
        });

        Nomorhp.setFont(new java.awt.Font("System Font", 1, 18)); // NOI18N
        Nomorhp.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Nomorhp.setText("Nomor Hp");
        Nomorhp.setAutoscrolls(true);

        textnomorhp.setColumns(20);
        textnomorhp.setFont(new java.awt.Font("System Font", 0, 14)); // NOI18N
        textnomorhp.setRows(5);
        textnomorhp.setMargin(new java.awt.Insets(5, 5, 5, 5));

        Email.setFont(new java.awt.Font("System Font", 1, 18)); // NOI18N
        Email.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Email.setText("Email");
        Email.setAutoscrolls(true);

        textemail.setColumns(20);
        textemail.setFont(new java.awt.Font("System Font", 0, 14)); // NOI18N
        textemail.setRows(5);
        textemail.setMargin(new java.awt.Insets(5, 5, 5, 5));

        Tgllahir.setFont(new java.awt.Font("System Font", 1, 18)); // NOI18N
        Tgllahir.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Tgllahir.setText("Tempat / Tanggal");
        Tgllahir.setAutoscrolls(true);

        Nisn.setFont(new java.awt.Font("System Font", 1, 18)); // NOI18N
        Nisn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Nisn.setText("NISN");
        Nisn.setAutoscrolls(true);

        textnisn.setColumns(20);
        textnisn.setFont(new java.awt.Font("System Font", 0, 14)); // NOI18N
        textnisn.setRows(5);
        textnisn.setMargin(new java.awt.Insets(5, 5, 5, 5));

        tableSiswa.setFont(new java.awt.Font("System Font", 1, 14)); // NOI18N
        tableSiswa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tableSiswa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableSiswaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableSiswa);

        jurusanbox.setFont(new java.awt.Font("System Font", 1, 18)); // NOI18N
        jurusanbox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Rekayasa Perangkat Lunak", "Multimedia", "Broadcasting", "Teknik Komputer Jaringan", "Teknik Elektronika Industri" }));
        jurusanbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jurusanboxActionPerformed(evt);
            }
        });

        Jurusan.setFont(new java.awt.Font("System Font", 1, 18)); // NOI18N
        Jurusan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Jurusan.setText("Jurusan");
        Jurusan.setAutoscrolls(true);

        texttempat.setColumns(20);
        texttempat.setFont(new java.awt.Font("System Font", 0, 14)); // NOI18N
        texttempat.setRows(5);
        texttempat.setMargin(new java.awt.Insets(5, 5, 5, 5));

        agama.setFont(new java.awt.Font("System Font", 1, 18)); // NOI18N
        agama.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        agama.setText("Agama");
        agama.setAutoscrolls(true);

        agamabox.setFont(new java.awt.Font("System Font", 1, 18)); // NOI18N
        agamabox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Islam", "Kristen", "Budha", "Hindu", "Konghucu", "Atheis", " " }));
        agamabox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agamaboxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(615, 615, 615)
                .addComponent(Header)
                .addGap(625, 625, 625))
            .addGroup(layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Nisn)
                        .addGap(85, 85, 85)
                        .addComponent(textnisn, javax.swing.GroupLayout.PREFERRED_SIZE, 477, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Kelamin)
                                    .addComponent(agama))
                                .addGap(55, 55, 55)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(laki, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(agamabox, 0, 1, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addComponent(perempuan)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(Nama)
                                            .addComponent(Umur)
                                            .addComponent(Umur1))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(495, 495, 495)
                                                .addComponent(Tgllahir))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(textnama, javax.swing.GroupLayout.PREFERRED_SIZE, 477, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(Nomorhp))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(textumur, javax.swing.GroupLayout.PREFERRED_SIZE, 477, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(Email))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(textalamat, javax.swing.GroupLayout.PREFERRED_SIZE, 477, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(Jurusan)))
                                        .addGap(49, 49, 49)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jurusanbox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(textemail)
                                                .addComponent(textnomorhp, javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addGroup(layout.createSequentialGroup()
                                                    .addComponent(texttempat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(tgllahir, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(ButtonEdit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(ButtonTambah)
                                            .addComponent(ButtonHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(ButtonClear, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(42, 42, 42))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(Header)
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textnisn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Nisn)
                                    .addComponent(Tgllahir))
                                .addGap(9, 9, 9)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Nomorhp, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(textnama, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(textnomorhp, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(textumur, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(textemail, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(28, 28, 28)
                                        .addComponent(Email))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addComponent(Nama)
                                .addGap(30, 30, 30)
                                .addComponent(Umur)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textalamat, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Umur1)
                            .addComponent(jurusanbox, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Jurusan)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ButtonTambah, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(texttempat, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tgllahir, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ButtonEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(58, 58, 58)
                                .addComponent(ButtonHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(14, 14, 14)
                                .addComponent(ButtonClear, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(laki)
                    .addComponent(perempuan)
                    .addComponent(Kelamin))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(agama)
                    .addComponent(agamabox, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    private void ButtonTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonTambahActionPerformed
        // TODO add your handling code here:
        tambahData();
    }//GEN-LAST:event_ButtonTambahActionPerformed

    private void ButtonEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonEditActionPerformed
        // TODO add your handling code here:
        editData();
    }//GEN-LAST:event_ButtonEditActionPerformed

    private void ButtonClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonClearActionPerformed
        // TODO add your handling code here:
        clearData();
    }//GEN-LAST:event_ButtonClearActionPerformed

    private void ButtonHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonHapusActionPerformed
        // TODO add your handling code here:
        hapusData();
    }//GEN-LAST:event_ButtonHapusActionPerformed

    private void perempuanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_perempuanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_perempuanActionPerformed

    private void tableSiswaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableSiswaMouseClicked
        // TODO add your handling code here:
        tableGet();
    }//GEN-LAST:event_tableSiswaMouseClicked

    private void jurusanboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jurusanboxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jurusanboxActionPerformed

    private void agamaboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agamaboxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_agamaboxActionPerformed

    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(form_siswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(form_siswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(form_siswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(form_siswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new form_siswa().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ButtonClear;
    private javax.swing.JButton ButtonEdit;
    private javax.swing.JButton ButtonHapus;
    private javax.swing.JButton ButtonTambah;
    private javax.swing.JLabel Email;
    private javax.swing.JLabel Header;
    private javax.swing.JLabel Jurusan;
    private javax.swing.JLabel Kelamin;
    private javax.swing.JLabel Nama;
    private javax.swing.JLabel Nisn;
    private javax.swing.JLabel Nomorhp;
    private javax.swing.JLabel Tgllahir;
    private javax.swing.JLabel Umur;
    private javax.swing.JLabel Umur1;
    private javax.swing.JLabel agama;
    private javax.swing.JComboBox<String> agamabox;
    private javax.swing.ButtonGroup buttonGroup1;
    private com.toedter.calendar.demo.DateChooserPanelBeanInfo dateChooserPanelBeanInfo1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> jurusanbox;
    private javax.swing.JRadioButton laki;
    private javax.swing.JRadioButton perempuan;
    private javax.swing.JTable tableSiswa;
    private javax.swing.JTextArea textalamat;
    private javax.swing.JTextArea textemail;
    private javax.swing.JTextArea textnama;
    private javax.swing.JTextArea textnisn;
    private javax.swing.JTextArea textnomorhp;
    private javax.swing.JTextArea texttempat;
    private javax.swing.JTextArea textumur;
    private com.toedter.calendar.JDateChooser tgllahir;
    // End of variables declaration//GEN-END:variables
}
