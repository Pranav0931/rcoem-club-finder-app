package com.hdaf.clubfinder.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.hdaf.clubfinder.ChatAdapter
import com.hdaf.clubfinder.ChatMessage
import com.hdaf.clubfinder.R

class ChatFragment : Fragment() {

    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    private lateinit var chatAdapter: ChatAdapter
    private val messagesList = mutableListOf<ChatMessage>()
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_chat, container, false)

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        recyclerView = view.findViewById(R.id.chat_recycler_view)
        val messageEditText = view.findViewById<EditText>(R.id.message_edit_text)
        val sendButton = view.findViewById<Button>(R.id.send_button)

        val layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = layoutManager
        // Pass the long-press handler to the adapter
        chatAdapter = ChatAdapter(messagesList) { message ->
            showDeleteConfirmationDialog(message)
        }
        recyclerView.adapter = chatAdapter

        sendButton.setOnClickListener {
            val messageText = messageEditText.text.toString().trim()
            if (messageText.isNotEmpty()) {
                sendMessage(messageText)
                messageEditText.text.clear()
            }
        }

        signInAnonymously()
        listenForMessages()

        return view
    }

    private fun showDeleteConfirmationDialog(message: ChatMessage) {
        AlertDialog.Builder(requireContext())
            .setTitle("Delete Message")
            .setMessage("Are you sure you want to delete this message?")
            .setPositiveButton("Delete") { _, _ ->
                deleteMessage(message)
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun deleteMessage(message: ChatMessage) {
        if (message.id.isEmpty()) {
            Toast.makeText(context, "Cannot delete this message.", Toast.LENGTH_SHORT).show()
            return
        }
        db.collection("chat").document(message.id)
            .delete()
            .addOnSuccessListener {
                Toast.makeText(context, "Message deleted.", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                // --- THIS LINE IS NOW CORRECTED ---
                Toast.makeText(context, "Failed to delete message.", Toast.LENGTH_SHORT).show()
            }
    }

    private fun signInAnonymously() {
        if (auth.currentUser == null) {
            auth.signInAnonymously().addOnCompleteListener(requireActivity()) { task ->
                if (!task.isSuccessful) {
                    Toast.makeText(context, "Anonymous authentication failed.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun sendMessage(text: String) {
        val senderId = auth.currentUser?.uid ?: return
        val senderName = "User-${senderId.substring(0, 5)}"
        val message = ChatMessage(text = text, senderName = senderName, senderId = senderId)

        db.collection("chat").add(message)
    }

    private fun listenForMessages() {
        db.collection("chat").orderBy("timestamp", Query.Direction.ASCENDING)
            .addSnapshotListener { snapshots, e ->
                if (e != null) {
                    return@addSnapshotListener
                }

                if (snapshots != null) {
                    messagesList.clear()
                    for (doc in snapshots) {
                        val message = doc.toObject(ChatMessage::class.java)
                        messagesList.add(message)
                    }
                    chatAdapter.updateMessages(messagesList)
                    recyclerView.scrollToPosition(messagesList.size - 1)
                }
            }
    }
}
