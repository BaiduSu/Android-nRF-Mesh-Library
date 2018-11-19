package no.nordicsemi.android.meshprovisioner;

import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import no.nordicsemi.android.meshprovisioner.utils.MeshParserUtils;

final class AllocatedUnicastRangeDeserializer implements JsonSerializer<List<AllocatedUnicastRange>>, JsonDeserializer<List<AllocatedUnicastRange>> {
    private static final String TAG = AllocatedUnicastRangeDeserializer.class.getSimpleName();

    @Override
    public List<AllocatedUnicastRange> deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context) throws JsonParseException {
        final List<AllocatedUnicastRange> unicastRanges = new ArrayList<>();
        try {
            final JsonArray jsonObject = json.getAsJsonArray();
            for (int i = 0; i < jsonObject.size(); i++) {
                final JsonObject unicastRangeJson = jsonObject.get(i).getAsJsonObject();
                final byte[] lowAddress = MeshParserUtils.toByteArray(unicastRangeJson.get("lowAddress").getAsString());
                final byte[] highAddress = MeshParserUtils.toByteArray(unicastRangeJson.get("highAddress").getAsString());
                unicastRanges.add(new AllocatedUnicastRange(lowAddress, highAddress));
            }
        } catch (Exception ex) {
            Log.e(TAG, "Error while de-serializing allocated unicast range: " + ex.getMessage());
        }
        return unicastRanges;
    }

    @Override
    public JsonElement serialize(final List<AllocatedUnicastRange> src, final Type typeOfSrc, final JsonSerializationContext context) {
        return null;
    }
}
