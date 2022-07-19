package com.bruno.tracker;

import com.bruno.tracker.models.Activity;
import com.bruno.tracker.models.ActivityMapper;
import com.bruno.tracker.models.Comment;
import com.bruno.tracker.models.CommentMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Controller
public class ActivityController {
    @Autowired
    JdbcTemplate Db;

    @GetMapping("/")
    public String index(@RequestParam(name = "author", required = false, defaultValue = "Bruno") String author, Model model) {
        List<Activity> activities = Db.query("SELECT * FROM activities;", new ActivityMapper());
        List<Comment> comments = Db.query("SELECT * FROM comments;", new CommentMapper());

        for (Activity activity : activities) {
            activity.Comments = comments.stream()
                    .filter(comment -> activity.Id == comment.ActivityId)
                    .sorted(Comparator.comparing(c -> c.CreationDate))
                    .collect(Collectors.toList());
        }

        model.addAttribute("author", author);
        model.addAttribute("activities", activities);

        return "index"; // Return the name of the HTML file in the /templates directory
    }

    @Transactional
    @PostMapping("/post-comment")
    public String saveComment(@RequestParam Map<String, String> body) throws Exception {
        Comment newComment = new Comment();

        for (var formField : body.entrySet()) {
            // TODO: Use proper object creation mechanisms from spring boot (html form -> java object)
            String value = formField.getValue();

            switch (formField.getKey()) {
                case "content" -> newComment.setContent(value);
                case "author" -> newComment.setAuthor(value);
                case "activity" -> newComment.setActivityId(value);
                default -> throw new Exception("Unexpected value in comment creation");
            }
        }

        Db.update("INSERT INTO comments (activity_id, author, content) VALUES (?, ?, ?);",
                newComment.getActivityId(), newComment.getAuthor(), newComment.getContent());

        // TODO: maybe just acknowledge that everything went ok instead of rendering the page again.
        return "redirect:?author=" + newComment.getAuthor(); // HACK: This is to stay as the same "user".
    }

    @Transactional
    @PostMapping("/delete-comment")
    public String deleteComment(@RequestParam Map<String, String> body) {
        // The "validation" that the request to delete a comment was made by
        // the same user that created that comment is made at the client side.
        Db.update("DELETE FROM comments WHERE id=?;", body.get("id"));

        return "redirect:?author=" + body.get("author"); // HACK: This is to stay as the same "user".
    }

    // TODO: Missing funcionality
    // - Update comments
    // - Create activities
}