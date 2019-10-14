package com.example.notesapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;
import com.google.firebase.firestore.WriteBatch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExamplesActivity extends AppCompatActivity {

    private static final String TAG = "ExamplesActivity";
    FirebaseFirestore firestore = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_examples);
    }

    public void createDocument(View view) {
        Toast.makeText(this, "createDocument", Toast.LENGTH_SHORT).show();

//        Map<String, Object> map = new HashMap<>();
//        map.put("name", "iPhone 11");
//        map.put("price", 699);
//        map.put("isAvailable", true);

        Product product = new Product("iPhone 11", 699, true);

        firestore.collection("products")
//                .add(map)
                .add(product)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "onSuccess: task was succesfull");
                        Log.d(TAG, "onSuccess: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d(TAG, "onSuccess: task was NOT succesfull");
                    }
                });


//        Map<String, Object> map = new HashMap<>();
//        map.put("text", "i wanna watch captain marvel again and again");
//        map.put("isCompleted", false);
//        map.put("created", new Timestamp(new Date()));
//
//        firestore.collection("notes")
//                .add(map)
//                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//                    @Override
//                    public void onSuccess(DocumentReference documentReference) {
//                        Log.d(TAG, "onSuccess: task was succesfull");
//                        Log.d(TAG, "onSuccess: " + documentReference.getId());
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Log.d(TAG, "onSuccess: task was NOT succesfull");
//                    }
//                });

//        Map<String, Object> map = new HashMap<>();
//        map.put("name", "Mac Pro");
//        map.put("price", 9999);
//        map.put("isAvailable", true);
//
//        FirebaseFirestore.getInstance()
//                .collection("products")
//                .add(map)
//                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//                    @Override
//                    public void onSuccess(DocumentReference documentReference) {
//                        Log.d(TAG, "onSuccess: Product is added succesfully");
//                        Log.d(TAG, "onSuccess: " + documentReference.getId());
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Log.e(TAG, "onFailure: ", e);
//                    }
//                });

    }

    public void readDocument(View view) {

        Toast.makeText(this, "Reading a doc...", Toast.LENGTH_SHORT).show();

        FirebaseFirestore.getInstance()
                .collection("products")
                .document("NH74yiFpjYwxakCKXjJj")
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            Product product = task.getResult().toObject(Product.class);
                            Log.d(TAG, "onComplete: " + product);
                        } else {
                            Log.e(TAG, "onComplete: ", task.getException() );
                        }
                    }
                });
//                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
//                    @Override
//                    public void onSuccess(DocumentSnapshot documentSnapshot) {
//                        Product product = documentSnapshot.toObject(Product.class);
//                        Log.d(TAG, "onSuccess: " + product);
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Log.e(TAG, "onFailure: ", e);
//                    }
//                });








        
//        FirebaseFirestore.getInstance()
//                .collection("products")
//                .document("NH74yiFpjYwxakCKXjJj")
//                .get()
//                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
//                    @Override
//                    public void onSuccess(DocumentSnapshot documentSnapshot) {
////                        Log.d(TAG, "onSuccess: " + documentSnapshot.getData());
////                        Log.d(TAG, "onSuccess name: " + documentSnapshot.getString("name"));
////                        Log.d(TAG, "onSuccess isAvailable: " + documentSnapshot.getBoolean("isAvailable"));
////                        Log.d(TAG, "onSuccess price: " + documentSnapshot.getLong("price"));
//                        Product product = documentSnapshot.toObject(Product.class);
//                        Log.d(TAG, "onSuccess: " + product.toString());
//                        Log.d(TAG, "onSuccess: " + product.getName());
//                        Log.d(TAG, "onSuccess: " +product.getPrice());
//                        Log.d(TAG, "onSuccess: " + product.getIsAvailable());
//
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Log.e(TAG, "onFailure: ", e);
//                    }
//                });

    }

    public void updateDocument(View view) {
//        Toast.makeText(this, "updateDocument", Toast.LENGTH_SHORT).show();

        final DocumentReference docRef = FirebaseFirestore.getInstance()
                                    .collection("products")
                                    .document("123");

        Map<String, Object> map = new HashMap<>();
//        map.put("name", "iPhone 6s Plus");
        map.put("price", FieldValue.increment(-100));
//        map.put("brand", FieldValue.delete());
//        map.put("isAvailable", true);

        docRef.update(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "onSuccess: yay, updated the doc");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e(TAG, "onFailure: ", e);
                    }
                });

//        docRef.set(map, SetOptions.merge())
//                .addOnSuccessListener(new OnSuccessListener<Void>() {
//                    @Override
//                    public void onSuccess(Void aVoid) {
//                        Log.d(TAG, "onSuccess: yay, set the doc");
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Log.e(TAG, "onFailure: ", e);
//                    }
//                });
    }

    public void deleteDocument(View view) {
        
//        FirebaseFirestore.getInstance().collection("products")
//                .document("123")
//                .delete()
//                .addOnSuccessListener(new OnSuccessListener<Void>() {
//                    @Override
//                    public void onSuccess(Void aVoid) {
//                        Log.d(TAG, "onSuccess: We have deleted the document...");
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Log.e(TAG, "onFailure: ", e);
//                    }
//                });

        FirebaseFirestore.getInstance().collection("products")
                .whereEqualTo("brand", "Apple")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                        WriteBatch batch = FirebaseFirestore.getInstance().batch();

                        List<DocumentSnapshot> snapshotList = queryDocumentSnapshots.getDocuments();
                        for (DocumentSnapshot snapshot: snapshotList) {
                            batch.delete(snapshot.getReference());
                        }

                        batch.commit()
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Log.d(TAG, "onSuccess: Deleted all docs with brand = Apple");
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.e(TAG, "onFailure: ", e);
                                    }
                                });

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });

        
    }

    public void getAllDocuments(View view) {
        Toast.makeText(this, "getAllDocuments", Toast.LENGTH_SHORT).show();

        FirebaseFirestore.getInstance()
                .collection("products")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        Log.d(TAG, "onSuccess: We're getting the data");

                        List<Product> productList = queryDocumentSnapshots.toObjects(Product.class);
                        Log.d(TAG, "onSuccess: " + productList.toString());

//                        List<DocumentSnapshot> snapshotList = queryDocumentSnapshots.getDocuments();
//                        for (DocumentSnapshot snapshot: snapshotList) {
//                            Log.d(TAG, "onSuccess: " + snapshot.getData().toString());
//                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e(TAG, "onFailure: ", e);
                    }
                });

//        FirebaseFirestore.getInstance()
//                .collection("products")
////                .whereLessThan("price", 1000)
////                .whereEqualTo("isAvailable", false)
//                .whereEqualTo("price", 999)
//                .get()
//                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
//                    @Override
//                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
//                        Log.d(TAG, "onSuccess: We're getting the data");
//                        List<DocumentSnapshot> snapshotList = queryDocumentSnapshots.getDocuments();
//                        for (DocumentSnapshot snapshot: snapshotList) {
//                            Log.d(TAG, "onSuccess: " + snapshot.getData().toString());
//                        }
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Log.e(TAG, "onFailure: ", e);
//                    }
//                });

//        firestore.collection("products")
//                .whereEqualTo("brand", "apple")
//                .get()
//                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
//                    @Override
//                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
//                        WriteBatch batch = firestore.batch();
//                        List<DocumentSnapshot> snapshotList = queryDocumentSnapshots.getDocuments();
//                        for (DocumentSnapshot snapshot: snapshotList) {
//                            snapshot.getDocumentReference(snapshot.getId());
//                            batch.delete(snapshot.getReference());
//                        }
//                        batch.commit()
//                                .addOnSuccessListener(new OnSuccessListener<Void>() {
//                                    @Override
//                                    public void onSuccess(Void aVoid) {
//                                        Log.d(TAG, "onSuccess: YAY");
//                                    }
//                                });
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Log.e(TAG, "onFailure: ", e);
//                    }
//                });

//        FirebaseFirestore.getInstance()
//                .collection("products")
//                .orderBy("price", Query.Direction.DESCENDING)
////                .orderBy("price", Query.Direction.DESCENDING)
////                .orderBy("name")
////                .orderBy("isAvailable", Query.Direction.DESCENDING)
//                .limit(1)
//                .get()
//                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
//                    @Override
//                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
//                        List<DocumentSnapshot> snapshotList = queryDocumentSnapshots.getDocuments();
//                        for (DocumentSnapshot snapshot: snapshotList) {
//                            Log.d(TAG, "onSuccess: " + snapshot.getData());
//                        }
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Log.e(TAG, "onFailure: ", e);
//                    }
//                });

    }

    public void getAllDocumentsWithRealtimeUpdates(View view) {

//        FirebaseFirestore.getInstance()
//                .collection("products")
//                .addSnapshotListener(new EventListener<QuerySnapshot>() {
//                    @Override
//                    public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
//                        if (e != null) {
//                            Log.e(TAG, "onEvent: ", e);
//                            return;
//                        }
//                        if (queryDocumentSnapshots != null) {
//                            Log.d(TAG, "onEvent: ---------------------------");
////                            List<DocumentSnapshot> snapshotList = queryDocumentSnapshots.getDocuments();
////                            for (DocumentSnapshot snapshot : snapshotList) {
////                                Log.d(TAG, "onEvent: " + snapshot.getData());
////                            }
//
//                            List<DocumentChange> documentChangeList = queryDocumentSnapshots.getDocumentChanges();
//                            for (DocumentChange documentChange: documentChangeList) {
//                                Log.d(TAG, "onEvent: " + documentChange.getDocument().getData());
//                            }
//                        } else {
//                            Log.e(TAG, "onEvent: query snapshot was null");
//                        }
//                    }
//                });

//        FirebaseFirestore.getInstance()
//                .collection("products")
//                .document("8UpzPzjyzV534fgi78P9")
//                .addSnapshotListener(new EventListener<DocumentSnapshot>() {
//                    @Override
//                    public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
//                        if (e != null) {
//                            Log.e(TAG, "onEvent: ",e );
//                            return;
//                        }
//                        if (documentSnapshot != null) {
//                            Log.d(TAG, "onEvent: -------------------");
//                            Log.d(TAG, "onEvent: " + documentSnapshot.getData());
//                        } else {
//                            Log.e(TAG, "onEvent: NULL");
//                        }
//                    }
//                });
        
    }
}

























































